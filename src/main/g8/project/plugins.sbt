addSbtPlugin("com.dwijnand" % "sbt-dynver" % "4.0.0")
addSbtPlugin("com.dwijnand" % "sbt-travisci" % "1.2.0")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.1")
// TODO: We have to add javax activation until this is fixed:
//  https://github.com/ohnosequences/sbt-github-release/issues/28
libraryDependencies += "com.sun.activation" % "javax.activation" % "1.2.0"
addSbtPlugin("ohnosequences" % "sbt-github-release" % "0.7.0")
addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.6")
