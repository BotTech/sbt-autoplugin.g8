# Setup

Follow these instructions once to setup your project and then you can delete this file.

## Git

### Create GitHub Repository

[Create a new repository] on GitHub.

> ⚠️ Do not initialize the repository with a README, .gitignore or license.

Once you have created the remote repository, copy the URL.

Now initialise the git repository locally:

```shell script
cd $name$
git init
git add .
git commit -m "Generate plugin from template"
git remote add origin git@github.com:$organizationName$/$name$.git
git push -u origin master
```

### GitHub Project Settings

Recommended [GitHub repository settings]:
- Allow squash merging (disabled)
- Allow rebase merging (disabled)
- Automatically delete head branches (enabled)

### Branch Protection Rules

Recommended [branch protection rules]:

First rule:
- Branch name pattern: `master`
- Require pull request reviews before merging
- Dismiss stale pull request approvals when new commits are pushed
- Require review from Code Owners
- Require status checks to pass before merging
- Require branches to be up to date before merging
- Status checks (you may need to come back to this once there are more builds)
- Require signed commits
- Include administrators

Second rule:
- Branch name pattern: `*`
- Require signed commits
- Include administrators
- Allow force pushes
- Allow deletions

### Projects

Setup the following [project boards]:

Board one:
- Project board name: Triage Queue
- Description: The board for triaging new issues.
- Project template: Bug triage

Board two:
- Project board name: Main
- Description: The main board.
- Project template: Automated kanban with reviews

> ℹ️ You may also want to consider [ZenHub] which is free for public, personal and academic
> repositories.

## Continuous Integration

Continuous integration builds are done with [Travis CI].

Head over to your [Travis CI organization profile] and enable the build on this project.

> ℹ️ You may need to sync the account if the project does not appear in the list.

## Scala Steward

You need to submit a [pull request to Scala Steward] to get it to automatically update dependencies.

## Publishing

### Create a PGP Key

You will need a PGP key to sign your artifacts. We will use [GnuPG].

> ⚠️ It is highly recommended to use [Tails] and install it onto a USB and then generate all your
> keys on that. You should never export your primary secret key off of this device. Use subkeys
> instead.

If this is the first time creating a PGP key then run:

```
❯ gpg --full-generate-key --expert
gpg (GnuPG/MacGPG2) 2.2.17; Copyright (C) 2019 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Please select what kind of key you want:
   (1) RSA and RSA (default)
   (2) DSA and Elgamal
   (3) DSA (sign only)
   (4) RSA (sign only)
   (7) DSA (set your own capabilities)
   (8) RSA (set your own capabilities)
   (9) ECC and ECC
  (10) ECC (sign only)
  (11) ECC (set your own capabilities)
  (13) Existing key
Your selection? 11

Possible actions for a ECDSA/EdDSA key: Sign Certify Authenticate
Current allowed actions: Sign Certify

   (S) Toggle the sign capability
   (A) Toggle the authenticate capability
   (Q) Finished

Your selection? s

Possible actions for a ECDSA/EdDSA key: Sign Certify Authenticate
Current allowed actions: Certify

   (S) Toggle the sign capability
   (A) Toggle the authenticate capability
   (Q) Finished

Your selection? q
Please select which elliptic curve you want:
   (1) Curve 25519
   (3) NIST P-256
   (4) NIST P-384
   (5) NIST P-521
   (6) Brainpool P-256
   (7) Brainpool P-384
   (8) Brainpool P-512
   (9) secp256k1
Your selection? 1
Please specify how long the key should be valid.
         0 = key does not expire
      <n>  = key expires in n days
      <n>w = key expires in n weeks
      <n>m = key expires in n months
      <n>y = key expires in n years
Key is valid for? (0) 1y
Key expires at Mon 19 Apr 14:20:51 2021 NZST
Is this correct? (y/N) y

GnuPG needs to construct a user ID to identify your key.

Real name: Alice
Email address: alice@example.com
Comment:
You selected this USER-ID:
    "Alice <alice@example.com>"

Change (N)ame, (C)omment, (E)mail or (O)kay/(Q)uit? o
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.
gpg: key 7A2A2EA4E8F9668A marked as ultimately trusted
gpg: revocation certificate stored as '/Users/alice/.gnupg/openpgp-revocs.d/DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A.rev'
public and secret key created and signed.

pub   ed25519 2020-04-19 [C] [expires: 2021-04-19]
      DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
uid                      Alice <alice@example.com>
```

> ℹ️ This generates a new primary key using Curve25519 which is only able to be used to create
> subkeys.

Export the public primary key so that we can share it:

```
❯ gpg --export --armor --output public.asc DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
```

Generate a revocation certificate:

```
❮ gpg --gen-revoke --output revocation-certificate.asc DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A

sec  ed25519/7A2A2EA4E8F9668A 2020-04-19 Alice <alice@example.com>

Create a revocation certificate for this key? (y/N) y
Please select the reason for the revocation:
  0 = No reason specified
  1 = Key has been compromised
  2 = Key is superseded
  3 = Key is no longer used
  Q = Cancel
(Probably you want to select 1 here)
Your decision? 1
Enter an optional description; end it with an empty line:
>
Reason for revocation: Key has been compromised
(No description given)
Is this okay? (y/N) y
ASCII armored output forced.
Revocation certificate created.

Please move it to a medium which you can hide away; if Mallory gets
access to this certificate he can use it to make your key unusable.
It is smart to print this certificate and store it away, just in case
your media become unreadable.  But have some caution:  The print system of
your machine might store the data and make it available to others!
```

> ℹ️ Store this `revocation-certifcate.asc` on another USB device, and ideally store it separately
> from your main Tails USB.

Now add a subkey:

```
❯ gpg --expert --edit-key DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
gpg (GnuPG/MacGPG2) 2.2.17; Copyright (C) 2019 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Secret key is available.

sec  ed25519/7A2A2EA4E8F9668A
     created: 2020-04-19  expires: 2021-04-19  usage: C
     trust: ultimate      validity: ultimate
[ultimate] (1). Alice <alice@example.com>

gpg> addkey
Please select what kind of key you want:
   (3) DSA (sign only)
   (4) RSA (sign only)
   (5) Elgamal (encrypt only)
   (6) RSA (encrypt only)
   (7) DSA (set your own capabilities)
   (8) RSA (set your own capabilities)
  (10) ECC (sign only)
  (11) ECC (set your own capabilities)
  (12) ECC (encrypt only)
  (13) Existing key
Your selection? 10
Please select which elliptic curve you want:
   (1) Curve 25519
   (3) NIST P-256
   (4) NIST P-384
   (5) NIST P-521
   (6) Brainpool P-256
   (7) Brainpool P-384
   (8) Brainpool P-512
   (9) secp256k1
Your selection? 1
Please specify how long the key should be valid.
         0 = key does not expire
      <n>  = key expires in n days
      <n>w = key expires in n weeks
      <n>m = key expires in n months
      <n>y = key expires in n years
Key is valid for? (0) 1y
Key expires at Mon 19 Apr 14:28:32 2021 NZST
Is this correct? (y/N) y
Really create? (y/N) y
We need to generate a lot of random bytes. It is a good idea to perform
some other action (type on the keyboard, move the mouse, utilize the
disks) during the prime generation; this gives the random number
generator a better chance to gain enough entropy.

sec  ed25519/7A2A2EA4E8F9668A
     created: 2020-04-19  expires: 2021-04-19  usage: C
     trust: ultimate      validity: ultimate
ssb  ed25519/2183A9797A90B5D4
     created: 2020-04-19  expires: 2021-04-19  usage: S
[ultimate] (1). Alice <alice@example.com>

gpg> save
```

Now we need to export the secret subkey that we just created:

```
❯ gpg --export-secret-subkeys --armor --output secret-subkeys.asc 2183A9797A90B5D4!
```

> ⚠️ The key id is the part after `ssb  ed25519/`. Don't forget to add the trailing `!`.

This has exported the secret subkey to `secret-subkeys.asc` but it has the passphrase of the primary
key. We don't want to ever have to enter this passphrase on any other device so we need to change
the passphrase of this subkey.

Import the subkey into a new home directory:

```
❯ mkdir /tmp/gnupg
❯ chmod 700 /tmp/gnupg
❯ gpg --homedir /tmp/gnupg --import secret-subkeys.asc
gpg: keybox '/tmp/gnupg/pubring.kbx' created
gpg: /tmp/gnupg/trustdb.gpg: trustdb created
gpg: key 7A2A2EA4E8F9668A: public key "Alice <alice@example.com>" imported
gpg: Total number processed: 1
gpg:               imported: 1
```

> ⚠️ If you get an error saying:
>
> ```
> gpg: key 7A2A2EA4E8F9668A/2183A9797A90B5D4: error sending to agent: No such file or directory
> ```
> 
> Then this probably has something to do with the previous gpg-agent using the old homedir. You can
> try to restart it:
>
> ```
> ❯ gpgconf --kill gpg-agent
> ❯ gpg-agent --homedir /tmp/gnupg --daemon
> ```

Double check that it was exported correctly:

```
❯ gpg --homedir /tmp/gnupg --list-secret-keys
/tmp/gnupg/pubring.kbx
-------------------------------
sec#  ed25519 2020-04-19 [C] [expires: 2021-04-19]
      DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
uid           [ unknown] Alice <alice@example.com>
ssb   ed25519 2020-04-19 [S] [expires: 2021-04-19]
```

> ⚠️ The primary key must have a `#` next to it otherwise it means that you accidentally exported
> the primary secret key. If that happens then you need to remove the `/tmp/gnupg` directory then
> re-export and re-import the secret subkey.

Now change the passphrase:

```
❯ gpg --homedir /tmp/gnupg --change-passphrase DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
gpg (GnuPG/MacGPG2) 2.2.17; Copyright (C) 2019 Free Software Foundation, Inc.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

gpg: key 7A2A2EA4E8F9668A/7A2A2EA4E8F9668A: error changing passphrase: No secret key
```

> ℹ️ You can ignore the error saying that there was an error changing the passphrase, it is
> referring to the primary key.

Export the secret subkey with the new passphrase:

```
❯ gpg --homedir /tmp/gnupg --export-secret-subkeys --armor --output secret-subkeys.asc 2183A9797A90B5D4!
File 'secret-subkeys.asc' exists. Overwrite? (y/N) y
```

Now you need to get the `public.asc` and `secret-subkeys.asc` files off of the Tails USB and onto
your development machine.

> ⚠️ Do not connect Tails to the internet to do this.

Once you have the `public.asc` primary public key on your development machine you should import it
and then publish it so that others can verify your signed artifacts:

```
❯ gpg --import public.asc
gpg: key 7A2A2EA4E8F9668A: public key "Alice <alice@example.com>" imported
gpg: Total number processed: 1
gpg:               imported: 1
❯ gpg --keyserver hkp://pool.sks-keyservers.net --send-keys DB055D46DFB26C23C2ED1C607A2A2EA4E8F9668A
```

> ℹ️ There are many public key servers. Pick whichever ones you like.

> ℹ️ You can repeat the steps above to also create other signing, encryption or authentication
> subkeys for personal use on your development machine.

> ℹ️ You can use a subkey with authentication capabilities for SSH authentication by using:
>
> `~/.profile`:
>
> ```
> export SSH_AUTH_SOCK="\$(gpgconf --list-dirs agent-ssh-socket)"
> gpgconf --launch gpg-agent
> ```
> 
> `~/.gitconfig`:
>
> ```
> [url "ssh://git@github.com"]
>         insteadOf = https://github.com
> ```
> 
> `~/.gnupg/gpg-agent.conf`:
>
> ```
> enable-ssh-support
> pinentry-program /usr/local/MacGPG2/libexec/pinentry-mac.app/Contents/MacOS/pinentry-mac
> ```
> 
> Then list your keys as normal:
>
> ```
> ❯ ssh-add -L
> ```

> ℹ️ You can also add your personal keys to a smartcard like a YubiKey 5C.

> ⚠️ Now that you are done with your Tails USB go and store it away somewhere safe like a fireproof
> safe.

Edit `build.sbt` to use this new subkey:

```sbt
usePgpKeyHex("2183A9797A90B5D4!")
```

> ℹ️ Do not forget the trailing `!`.

### Travis CI Environment Variables

Go to the [Travis CI build settings] and add the ASCII armored key and passphrase as `PGP_KEY` and
`PGP_PASSPHRASE` respectively. You can make these available only to your release branch. 

> ⚠️ Do not use the encrypted variables or files which are stored in the repository. If you were to
> ever > move this repository to someone else's organization then they would have access to your
> subkey. If this ever did happen then you can revoke the subkey but that will invalidate all
> existing signatures.

### Travis CI GitHub Token

Go to GitHub and create a Personal access token with the following scopes:
* `user:email`
* `read:org`
* `repo_deployment`
* `repo:status`
* `write:repo_hook`

See [Travis CI for open source projects] on what these scopes are used for.

Save the token somewhere safe as you will need it to login to the Travis CLI and if you forget it
you will need to generate a new one.

### Bintray

Create an [OSS Bintray account].

Add a new repository:
* Public
* Name: `sbt-plugins`
* Type: `Generic`

Go to your [Bintray profile] and copy your API key then add it as a new Travis CI environment
variable called `BINTRAY_PASS`. Also add your Bintray user name to the `BINTRAY_USER` environment
variable.

### GitHub OAuth Token

[ohnosequences/sbt-github-release] is used to publish the artifacts and create the GitHub release.

Generate a [new personal access token] for use in the build which has the following scopes:
* `repo`

Add this as another build environment variable called `GITHUB_TOKEN`.

### Promote Your Plugin

Once you have your first release it is time to promote your plugin!

1. Include your plugin in the [community sbt repository].
1. Add your plugin to the [community plugins list].
1. Add your plugin to the [Awesome Scala] list.
1. [Claim your project] in the Scaladex.

[awesome scala]: https://github.com/lauris/awesome-scala
[bintray profile]: https://bintray.com/profile/edit
[branch protection rules]: https://github.com/BotTech/sbt-turtles/settings/branches
[claim you project]: https://github.com/scalacenter/scaladex-contrib#claim-your-project
[community plugins list]: https://github.com/sbt/website#attention-plugin-authors
[community sbt repository]: https://www.scala-sbt.org/1.x/docs/Bintray-For-Plugins.html#Linking+your+package+to+the+sbt+organization
[create a new repository]: https://github.com/organizations/$organizationName$/repositories/new
[github repository settings]: https://github.com/$organizationName$/$name$/settings
[gnupg]: https://gnupg.org/
[new personal access token]: https://github.com/settings/tokens/new?description=$name$-release&scopes=repo
[ohnosequences/sbt-github-release]: https://github.com/ohnosequences/sbt-github-release
[oss bintray account]: https://bintray.com/signup/oss
[project boards]: https://github.com/$organizationName$/$name$/projects
[pull request to Scala Steward]: https://github.com/scala-steward-org/repos/blob/master/repos-github.md
[tails]: https://tails.boum.org/
[travis ci]: https://travis-ci.com
[travis ci build settings]: https://travis-ci.com/github/$organizationName$/$name$/settings
[travis ci for open source projects]: https://docs.travis-ci.com/user/github-oauth-scopes/#travis-ci-for-open-source-projects
[travis ci organization profile]: https://travis-ci.com/profile/$organizationName$
[zenhub]: https://www.zenhub.com/
