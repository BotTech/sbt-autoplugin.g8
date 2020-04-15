import sbt._
import sbt.Keys._

import sbttravisci.TravisCiPlugin.autoImport._

object ScalaVersionCheckPlugin extends AutoPlugin {

  override def trigger = allRequirements

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    Global / onLoad := (Global / onLoad).value.andThen { s =>
      val v = scala212.value
      if (!CrossVersion.isScalaApiCompatible(v))
        throw new MessageOnlyException(
          s"Key scala212 doesn't define a scala version. Check .travis.yml is setup right. Version: $v"
        )
      s
    }
  )
}
