# Gatling Example

Example of using Gatling for API Testing.

## Pre-Requisites

1. [Java JDK](https://www.oracle.com/java/technologies/downloads/)
1. [VSCode](https://code.visualstudio.com/download)
1. [Maven](https://maven.apache.org/install.html)

VSCode Extensions:
1. Scala Metals
1. Scala Syntax (official)
1. Maven for Java 

## API Under Test

The API required for testing is [here](https://github.com/fatmanmclone90/wiremock-example.)

## Executing the Test

In terminal:

```
    mvn clean -e gatling:test -DbaseUrl="http://localhost:8080" -DdurationSeconds="60" -DnumberOfMessages="120" -DapiKey="wiremock" "-Dgatling.simulationClass=com.next.simulations.LoadTest"
```

## Test Report

After execution HTML test report can be found at `C:\next\Whds.DummyProvider.PerformanceTests\Source\target\gatling\`

## Test Log

Test log can be found at: `Source/gatlig.log`.  See `Source/src/test/resources/logback.xml` for configuration.