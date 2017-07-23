// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `$name;format="normalize"$` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.log4jApi,
        library.log4jCore,
        library.log4jSlf4jImpl,
        library.typesafeConfig,
        library.mockito    % Test,
        library.scalaCheck % Test,
        library.scalaTest  % Test
      )
    )


// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
    pbSettings


lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.2",
    crossScalaVersions := Seq(scalaVersion.value, "2.11.8"),
    organization := "$organization$",
    version := "$version$",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ywarn-unused-import"
    ),
    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8"
    )
  )

import com.trueaccord.scalapb.compiler.Version.scalapbVersion
lazy val pbSettings =
  Seq(
    PB.protoSources.in(Compile) := Seq(baseDirectory.in(Compile).value / "proto"),
    PB.targets.in(Compile) := Seq(scalapb.gen() -> sourceManaged.in(Compile).value),
    libraryDependencies ++= Seq(
      "com.trueaccord.scalapb" %% "scalapb-runtime"      % scalapbVersion % "protobuf",
      "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion,
      "io.grpc"                % "grpc-netty"            % "1.4.0"
    )
  )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val log4j          = "2.8.2"
      val mockito        = "2.8.47"
      val scalaCheck     = "1.13.5"
      val scalaTest      = "3.0.3"
      val typesafeConfig = "1.3.1"
    }
    val log4jApi       = "org.apache.logging.log4j" % "log4j-api"        % Version.log4j
    val log4jCore      = "org.apache.logging.log4j" % "log4j-core"       % Version.log4j
    val log4jSlf4jImpl = "org.apache.logging.log4j" % "log4j-slf4j-impl" % Version.log4j
    val mockito        = "org.mockito"              % "mockito-core"     % Version.mockito
    val scalaCheck     = "org.scalacheck"           %% "scalacheck"      % Version.scalaCheck
    val scalaTest      = "org.scalatest"            %% "scalatest"       % Version.scalaTest
    val typesafeConfig = "com.typesafe"             % "config"           % Version.typesafeConfig
  }
