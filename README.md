# sbt-autoplugin.g8

[![Build Status]](https://travis-ci.org/BotTech/sbt-autoplugin)

A [Giter8][g8] template to create an [sbt AutoPlugin].

AutoPlugins are the recommended way to create sbt plugins for sbt versions 0.13.5 and up. This template creates a plugin
for sbt 1.x.

The original [sbt/sbt-autoplugin.g8][Original Repo] template was intended to contain the minimum amount to get started.
This fork is more comprehensive and opinionated, with the aim of requiring the minimum amount of configuration to get
the plugin published.

## Plugins

The generated project uses the following sbt plugins:
* [dwijnand/sbt-dynver] - dynamically determines version numbers from git tags.
* [dwijnand/sbt-travisci] - sets the Scala and SBT versions from the Travis CI information.
* [ohnosequences/sbt-github-release] - publishes releases to [GitHub].
* [sbt/sbt-bintray] - publishes releases to [Bintray].
* [sbt/sbt-pgp] - creates signatures of the artifacts with GnuPG.

## Usage

1. [Install sbt 1.0.0 or higher][sbt Setup]
1. On the command line type:
   ```bash
   sbt new BotTech/sbt-autoplugin.g8
   ```
1. You will be prompted for a few basic settings for your project.
1. sbt will create a plugin project for you.
1. Follow the instructions in `SETUP.md`.

## Credits

Credit to these Open Source projects:
* [dwijnand/sbt-dynver]
* [dwijnand/sbt-travisci]
* [ohnosequences/sbt-github-release]
* [sbt/sbt-bintray]
* [sbt/sbt-pgp]

Special thanks to:
* [GitHub] for hosting the git repository.
* [Lightbend] for [Scala] and [sbt].
* [scalacenter] for [Scala].
* [Travis CI] for running the build.
* All the other contributors who made this project possible.

# Licenses

This Giter8 template is distributed under a [CC0 1.0 Universal](LICENSE).

[Bintray]: https://bintray.com
[Build Status]: https://travis-ci.org/BotTech/sbt-gpg.svg?branch=master
[dwijnand/sbt-dynver]: https://github.com/dwijnand/sbt-dynver
[dwijnand/sbt-travisci]: https://github.com/dwijnand/sbt-travisci
[g8]: http://www.foundweekends.org/giter8
[Github]: https://github.com
[Lightbend]: https://www.lightbend.com
[ohnosequences/sbt-github-release]: https://github.com/ohnosequences/sbt-github-release
[Original Repo]: https://github.com/sbt/sbt-autoplugin.g8
[sbt]: https://www.scala-sbt.org
[sbt AutoPlugin]: http://www.scala-sbt.org/1.x/docs/Plugins.html#Creating+an+auto+plugin
[sbt Setup]: http://www.scala-sbt.org/release/docs/Setup.html
[sbt/sbt-bintray]: https://github.com/sbt/sbt-bintray
[sbt/sbt-pgp]: https://github.com/sbt/sbt-pgp
[Scala]: https://www.scala-lang.org
[scalacenter]: https://scala.epfl.ch
[Travis CI]: https://travis-ci.org
