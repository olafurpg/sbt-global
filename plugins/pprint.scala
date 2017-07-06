import sbt._, Keys._
import scala.xml.{Node => XmlNode, NodeSeq => XmlNodeSeq, _}
import scala.xml.transform.{RewriteRule, RuleTransformer}
import sbt.plugins.JvmPlugin

object PPrintPlugin extends AutoPlugin {
  override def requires = JvmPlugin
  override def trigger: PluginTrigger = AllRequirements
  override def projectSettings: Seq[Def.Setting[_]] = List(
    libraryDependencies ++= {
      if (scalaVersion.value.startsWith("2."))
        List("com.lihaoyi" %% "pprint" % "0.5.2" % Provided)
      else Nil
    },
    // (optional), if you publish libraries from your local computer then make
    // sure to exclude pprint from your published POM.
    pomPostProcess := { node =>
      new RuleTransformer(new RewriteRule {
        private def isPPrint(node: XmlNode): Boolean = {
          def isArtifactId(node: XmlNode, fn: String => Boolean) =
            node.label == "artifactId" && fn(node.text)
          node.label == "dependency" && node.child.exists(child =>
            isArtifactId(child, _.startsWith("pprint_")))
        }
        override def transform(node: XmlNode): XmlNodeSeq = node match {
          case e: Elem if isPPrint(node) => Text("")
          case _ => node
        }
      }).transform(pomPostProcess.value(node)).head
    }
  )
}
