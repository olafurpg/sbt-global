addSbtPlugin("org.duhemm" % "sbt-errors-summary" % "0.3.0")

addSbtPlugin("com.scalapenos" % "sbt-prompt" % "0.2.1")

// These seem to be required by sbt-sonatype...
libraryDependencies += "commons-logging" % "commons-logging-api" % "1.1"

libraryDependencies += "commons-codec" % "commons-codec" % "1.3"
