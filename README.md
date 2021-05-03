# Rutgers 01:198:112 Data Structures Assignment 5 (Friends) Project Template with JUnit Tests

This repository contains a project template for the fifth programming assignment of
Rutgers 01:198:112 (Data Structures) along with working JUnit tests that utilise the test
cases in [this repository](https://github.com/USMC1941/CS112-Rutgers/tree/master/Assignments/Friends/testcases).

## Instructions

### Set Up

Paste the contents of your `Friends.java` file into `app/src/main/java/friends/Friends.java`.

### Running Locally

You can run FriendsApp with your Friends class by executing the following command from the command-line:

Mac/Linux:
```
./gradlew --console plain run
```

Windows:
```
.\gradlew.bat --console plain run
```

### Running the Unit Tests

Two scripts (one for Mac/Linux and one for Windows) are provided for running the tests and seeing their output. If
you are on Mac or Linux, the script is simply called `runtests`. If you are on Windows, it is called `runtests.bat`.

On Mac and Linux, you may execute the script via the following command:
```
./gradlew test
```

On Windows, use
```
.\gradlew.bat test
```
