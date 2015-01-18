# Project versioning

The project lifecycle versioning follows the [Semantic Versioning](http://semver.org/) guidelines. When creating Pull 
Requests to an in-development version branch, be aware of the level of changes you commit. This versioning scheme relies
on build automation in conjunction with [Continuous Integration](http://en.wikipedia.org/wiki/Continuous_integration).

## Version branches

For each potentially releasable version of the project a branch following SemVer versioning is created. The first
version should always be `v0.1.0`. The version branches are identified by prefixing the branch with `v` followed by 
three dot-separated positive numbers. Whenever work begins on a new version the branch should be forked from previous
version. That is, version `v0.1.1` should be forked from `v0.1.0` and later on `v1.0.0` from `v0.1.1`.
 
This scheme is admittedly somewhat convoluted when compared to eg. [git flow](http://nvie.com/posts/a-successful-git-branching-model/).
The reasoning for this is to produce a repository with a clearly visible history of versions and to allow the
developers to use Git tooling to generate eg. changelogs easily with human readable names. Another benefit of such
explicit versioning scheme is to allow build automation tools to report possible SemVer specific API change conflicts in 
each Pull Request before merging making the git flow's more traditional integration and bugfixing phase part of the 
normal development practice instead of an afterthought.

## Releases 

All releases are marked to the repository with [annotated tags](http://git-scm.com/book/en/v2/Git-Basics-Tagging#Annotated-Tags).
The releases may be preceded by commonly used pre-release versions, namely Alpha, Beta and RC. Whether these are used is
up to the project itself. GA for final releases may be optionally used. This tagging should be handled entirely by build 
automation.

Usage of snapshots should be avoided whenever possible. This is not a hard set rule and sometimes unavoidable,
especially in the early stages of project's lifecycle where no actual releases have occurred yet. In such case the
snapshot pre-release should not be visible in the repository at all as it is just clutter.

The name of the tag should follow Semantic Versioning's rules exactly. This means the third release candidate for
version 2.3.1 of the project would be tagged as `2.3.1-rc.3`. Note that releases should be considered eternal. If a 
majorly broken version was released by accident, a new patch version must be released following the normal version
increment rules. It is fine to remove the actual release from artifact repositories but not from the Git repository
itself - we learn from our mistakes and thus don't hide them, not even when it hurts.

# Working with the repository

Fork the entire repository. Make all changes in your local repository first. Do not push tags. Be aware of multi source
repositories, especially when submitting patches which modify build files.

## Submitting patches
 
Use Pull Requests. Name of your branch does not matter as long as it doesn't collide with the reserved branch patterns
as described in this document.

A good commit message explains what changed and why. It is fine to have the message in either past or current tense. If
submitting a multi commit Pull Request, only the first commit needs to follow this rule. Be sensible though, one word
commits are not beneficial to anyone.

It is up to your personal discretion whether you rebase/squash your branch before creating a Pull Request.

When resolving a GitHub issue, edit the Pull Request message to contain the trigger keyword (*see [Closing issues via commit messages](https://help.github.com/articles/closing-issues-via-commit-messages/)*).
Do not include the issue reference directly to the actual commit message.

## Reviewing Pull Requests

Allow the build automation (if any) to verify the Pull Request before merging. Other review policies should be agreed
by the people working on the project, but it is generally advisable to have at least some kind of peer review process
whenever possible, even in two person teams.