credentials += Credentials(
  "Sonatype Nexus Repository Manager",
  "oss.sonatype.org",
  sys.env.getOrElse("SONATYPE_USER", ""),
  sys.env.getOrElse("SONATYPE_PASS", "")
)

commands += Command.command("clear") { state =>
  val cr = new jline.console.ConsoleReader()
  cr.clearScreen
  state
}

triggeredMessage in ThisBuild := Watched.clearWhenTriggered
