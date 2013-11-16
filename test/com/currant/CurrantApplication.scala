package com.currant

import org.specs2.mutable.Around
import org.specs2.specification.Scope
import java.util.UUID
import com.jolbox.bonecp.{BoneCPConfig, BoneCP}
import play.api.test.FakeApplication
import org.specs2.execute.{Result, AsResult}
import play.api.test.Helpers.running
import play.api.Logger

trait CurrantApplication extends Around with Scope {

  val dbUrl = "jdbc:postgresql://localhost:5432/currant"
  val dbId = "database_" + UUID.randomUUID().toString.replaceAll("-", "_")


  val bcpConfig = {
    val b = new BoneCPConfig()
    b.setUsername("integration_test_currant")
    b.setPassword("integration_test_currant")
    b.setDisableJMX(true)
    b.setJdbcUrl(dbUrl)
   // b.setLazyInit(true)

   /* b.setMaxConnectionsPerPartition(1)
    b.setPartitionCount(1)*/
    b
  }

  try{

    Class.forName("org.postgresql.Driver")
  }catch {
    case e : Exception => println(e)
  }
  val adminBcp : BoneCP = new BoneCP(bcpConfig)

  def createNewDatabase() = {
    val c = adminBcp.getConnection
    Logger.debug("creating new database")
    c.createStatement().execute(s"create database $dbId")
    c.close()
  }

  val adlCfg = Map("db.default.url" -> s"jdbc:postgresql://localhost:5432/$dbId", "db.default.driver"->"org.postgresql.Driver")

  object CurrantFakeApplication extends FakeApplication(additionalConfiguration = adlCfg)

  def around[T](t: => T)(implicit x: AsResult[T]): Result = {
    Logger.debug("creating new database")
    createNewDatabase()
    try {
      val p = running(CurrantFakeApplication) {
        t
      }
      x.asResult(p)
    } finally {
      Logger.debug("dropping database")
      dropDatabase()
      Logger.debug("shutdown database")
      adminBcp.shutdown()
    }

  }

  def dropDatabase() {
    Class.forName("org.postgresql.Driver")
    Logger.debug("trying for connection")
    val c = adminBcp.getConnection
    Logger.debug("obtained connection")
    c.createStatement().execute(s"drop database $dbId")
    c.close()
    Logger.debug("database dropped")
  }
}