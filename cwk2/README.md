# Coursework 2

This project can be managed from the command line using [Gradle][1].
You don't need to install Gradle yourself, as a script is included that
will download it automatically if necessary.  You can also develop in
the IntelliJ IDE - by using the *Import Project* option and specifying
Gradle.  The instructions below assume the use of the command line.

## Source Code Location

Put all of the Kotlin source files that make up your solution in the
directory `src/main/kotlin/comp3222/cwk2`.  Also, make sure that each
Kotlin source file includes a package definition at the top:

    package comp3222.cwk2

## Building The Application

You can build the application on the command line with

    ./gradlew build

Omit the `./` on Windows.  Note that this will be fairly slow the first
time it runs, as it will need to download Gradle itself and the project's
dependencies.  Subsequent builds should be a lot faster.

## Running The Application

### With Gradle

You can run the application without command line arguments using

    ./gradlew run

This will work provided that you've written a suitable `main` function in
one of your `.kt` files.  Note that the the `run` task will trigger a
rebuild of the application when necessary, so there is not really any need
to execute the `build` task separately.

Use the `--args` option if you wish to supply command line arguments to
the application.  For example, to specify a data filename as the sole
command line argument, you do this:

    ./gradlew run --args data/bradford.txt

To specify a data filename and a year as command line arguments, you
need to enclose both in quotes, like this:

    ./gradlew run --args "data/bradford.txt 2018"

### Outside of Gradle

You can generate a self-contained executable JAR file for the application
with

    ./gradlew jar

This will appear in the `build/libs` subdirectory, as the file `cwk2.jar`.
It can be run with commands like this:

    java -jar cwk2.jar
    java -jar cwk2.jar ../../data/bradford.txt

## Submitting Your Code

Code should be submitted to Minerva as a Zip archive.  You can create a
suitable Zip archive with

    ./gradlew submit

[1]: https://gradle.org
