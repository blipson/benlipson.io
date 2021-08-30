lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """benlipson-io""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.scalamock" %% "scalamock" % "5.1.0" % Test,
      "net.liftweb" %% "lift-json" % "3.4.3",
      "net.liftweb" %% "lift-webkit" % "3.4.3"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
