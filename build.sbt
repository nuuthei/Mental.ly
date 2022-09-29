val scala3Version = "3.2.0-RC4"

lazy val root = (project in file("."))
  .settings(
    name := "Weather API",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.2",
      "io.circe" %% "circe-generic" % "0.14.2",
      "io.circe" %% "circe-parser" % "0.14.2"
    )
)
