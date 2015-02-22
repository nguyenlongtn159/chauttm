import play.Project._

name := "warehouse"

version := "1.0"

playJavaSettings

libraryDependencies ++= Seq (
  javaEbean,
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
  filters
  )