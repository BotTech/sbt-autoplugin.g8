# Contributing

All contributions are welcome.

Take a look at the [Main Board] and see if there is anything in the `To do` column which you would
like to work on. The highest priority issues are at the top.

You may also look for any issues that are labeled with [help wanted].

Just because an issue does not have the [help wanted] label does not mean that help is not
appreciated just double check with someone before picking it up.

Add a comment to the ticket to say that you are working on the issue and we will assign it to you
and update the relevant boards.

## Verified Commits

Make sure that you sign all your commits. See [Github - Signing Commits] for more details.

## Testing

All bug fixes should first have a test which demonstrates the bug. You should commit the failing
test before fixing it so reviewers can see that the test is doing what it is supposed to.

### Unit Tests

Run `test` for regular unit tests.

### Plugin Tests

Run `scripted` for scripted plugin tests.

## Versioning

Version numbers are determined automatically using [dwijnand/sbt-dynver].

To create a new version add a new git annotated tag:
```bash
git tag -as v1.1.0
```

> ⚠️ The tag must be annotated (`-a`) and signed (`-s`).

## Continuous Integration

Continuous integration builds can be found in [Travis CI].

## Publishing

The Travis CI build will automatically publish to [Bintray] and [GitHub] for all tagged commits on
master.

### Signed Artifacts

The published artifacts are signed with key belonging to [$organizationName$]. These are not
available in forks. Reach out to us if there are any issues with this.

### Bintray

This uses [sbt/sbt-bintray] to publish artifacts to Bintray and [ohnosequences/sbt-github-release]
to publish to GitHub.

[$organizationName$]: https://github.com/$organizationName;format="word"$
[bintray]: https://bintray.com
[dwijnand/sbt-dynver]: https://github.com/dwijnand/sbt-dynver
[github]: https://github.com
[github - signing commits]: https://help.github.com/articles/signing-commits/
[help wanted]: https://github.com/$organizationName;format="word"$/$name;format="norm"$"/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22
[main board]: https://github.com/$organizationName;format="word"$/$name;format="norm"$"/projects/1
[ohnosequences/sbt-github-release]: https://github.com/ohnosequences/sbt-github-release
[sbt/sbt-bintray]: https://github.com/sbt/sbt-bintray
[testing sbt plugins]: http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html
[travis ci]: https://travis-ci.com/github/BotTech/sbt-turtles
