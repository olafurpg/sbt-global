import sbt._, Keys._
import sbt.plugins.JvmPlugin

object PPrintPlugin extends AutoPlugin {
  override def requires = JvmPlugin
  override def trigger: PluginTrigger = AllRequirements
  override def projectSettings: Seq[Def.Setting[_]] = List(
    libraryDependencies ++= {
      if (scalaVersion.value.startsWith("2.")) List("com.lihaoyi" %% "pprint" % "0.5.2")
      else Nil
    }
  )
}
