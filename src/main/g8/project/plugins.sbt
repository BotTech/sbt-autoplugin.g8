addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.4")

// TODO: We have to add javax activation until this is fixed:
//  https://github.com/ohnosequences/sbt-github-release/issues/28
libraryDependencies += "com.sun.activation" % "javax.activation" % "1.2.0"
addSbtPlugin("ohnosequences" % "sbt-github-release" % "0.7.0")

addSbtPlugin("com.dwijnand" % "sbt-dynver" % "3.1.0")

addSbtPlugin("nz.co.bottech" % "sbt-gpg" % "1.0.0")
