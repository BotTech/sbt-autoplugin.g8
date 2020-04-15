#!/usr/bin/env bash

set -euxo pipefail

## cross publish everything when Scala 2.12.x is selected.
if [[ "$TRAVIS_SCALA_VERSION" =~ 2\.1[2]\..* && "${TRAVIS_PULL_REQUEST}" == "false" && "${TRAVIS_REPO_SLUG}" == "sbt/sbt-pgp" ]]; then
  RELEASE_COMBO=true;
fi

verPat="[0-9]+\.[0-9]+\.[0-9]+(-[A-Za-z0-9-]+)?"
tagPat="^v$verPat(#.*)?$"

if [[ "$TRAVIS_TAG" =~ $tagPat ]]; then
  tagVer=${TRAVIS_TAG}
  tagVer=${tagVer#v}   # Remove `v` at beginning.
  tagVer=${tagVer%%#*} # Remove anything after `#`.
  publishVersion='set every version := "'$tagVer'"'

  if [ "$RELEASE_COMBO" = "true" ]; then
    java -version
    echo "Releasing $tagVer with Scala $TRAVIS_SCALA_VERSION"

    ## change this to match your encrypted key
    openssl aes-256-cbc -K $encrypted_fcdd190fa04a_key -iv $encrypted_fcdd190fa04a_iv -in .travis/secret-key.asc.enc -out .travis/secret-key.asc -d
    echo $PGP_PASSPHRASE | gpg --passphrase-fd 0 --batch --yes --import .travis/secret-key.asc

    ## change this to match your build
    sbt "$publishVersion" "clean" "+publishSigned"
  fi
fi
