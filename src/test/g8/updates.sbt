dependencyUpdatesFailBuild := true

// We need this until https://github.com/rtimush/sbt-updates/issues/62 is done.
dependencyUpdatesFilter -= moduleFilter("org.scala-lang", "scala-library")
