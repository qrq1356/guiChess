# disclaimer.
this repository remote is purely for backup purposes\
it may be submitted with my assignment for comp603's "Software Development Project 2" at AUT -qrq1356

this is not a public repository, and is not intended to be used as such.\
no PRs, issues, or any other form of contribution will be accepted.

if you are a student with AUT, having access to this repository is not permission to copy my work.

# guiChess
A proof-of-concept chess game written with Java8 using Swing, Awt and derbyDB.

## features
- [x] move validation
    - [x] per piece
    - [x] per player
    - [x] prevent moving into check
- [x] check validation
- [x] graphical user interface
    - [x] user management
    - [x] game management
    - [x] game state
      - [x] board
      - [x] pieces
      - [x] move history / indication
      - [x] check indication
- [x] database
    - [x] user management
    - [x] game management
    - [x] game state
      - [x] move history
      - [x] result history

## netbeans setup
***these are personal notes and not instructions for any marker.***
this repository should *not* be considered my submission for the body of this assignment. it is purely for the GIT portion of the rubric.\
parts of the assignment are missing from this mirror purposefully, e.g. derby bin and lib directories, build and test output and any compiled classes etc.\
**for my intended submission body**, please see the attached zip file named as per the assignment requirements. this *will* include all relevant files and be a whole netbeans project ready for use without setup. although all files are included they are not all critical, and are included for completeness’s sake, given the same JDK version as mine is used the environment should be identical as it is quite literally the entire working local environment.

- Git, netbeans implementation of a git client is difficult to use now given the convention changes since netbeans 8.2 had a feature update. I strongly recommend using an alternative git client, it is however fine to use to visualize diffs from HEAD to local.
- .gitignore, this file is purposefully missing from this remote as it contains largely irrelevant handling
- derbyDB, this is a database implementation that is included with the JDK, the version used personally is not required at runtime for the built package. however for use in NetBeans database management service the exact bin is recomended. the exact version used was supplied to me by AUT, i will include it in the commit before submission for completeness ONLY.
- swing forms were not used so no netbeans specific setup is required, note forms seem to largely have no conventional implementation and can IDE lock a project.
- unit testing, NetBeans unit testing generation was not used so requires no setup.

## My personal environment
this is for the purpose of being complete, it is not required that runtime or build/debug time dependencies match these exactly.\
the versions I recommend are the latest stable release of all dependencies at the time of writing, additionally the JDK package should be from Oracle as that's what NetBeans seems to expect and what AUT has linked to, its likely fine to use alternatives but I have not tested this.

- Netbeans Project Version: NetBeans IDE 8.2 (Build 201705191307)
  - setup supplied by AUT
  - self updated to the noted build.
- java version: 1.8.0_371
  - from AUT recommended source (Oracle).
  - self updated to the noted build from the stable installer.
- derbyDB version (bin/lib): 10.14.2.0
    - supplied by AUT
- Windows version: 22H2 OS Build 22621.1702
  - this is the latest consumer channel release of windows11 at the time of writing.
  - not recommended, the new UI class is inconsistent with the metro UI class Swing falls back to.
  - either use an OS with a fully supported UI class, or use a Windows version before the UI class change (pre Windows 11)