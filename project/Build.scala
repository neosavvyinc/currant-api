import sbt._
import Keys._
import play.Project._
import de.johoop.jacoco4sbt._
import JacocoPlugin._


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

  lazy val s = Defaults.defaultSettings ++ Seq(jacoco.settings:_*)

  val main = play.Project(appName, appVersion, appDependencies, settings = s).settings(
    parallelExecution     in jacoco.Config := false,
    jacoco.reportFormats  in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8")),
    jacoco.excludes       in jacoco.Config := Seq("views.*", "controllers.Reverse*", "controllers.javascript.*", "controllers.ref.*", "Routes*")
  )




}
