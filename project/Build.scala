import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "currant"
  val appVersion      = "1.0-SNAPSHOT"

  val PostgreSQL = "postgresql" % "postgresql" % "9.1-901.jdbc4"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    PostgreSQL
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
