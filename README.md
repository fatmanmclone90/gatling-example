# Gatling Example

Example of using Gatling for API Testing.

## Pre-Requisites

1. [Java JDK](https://www.oracle.com/java/technologies/downloads/) 18.0.2
1. [VSCode](https://code.visualstudio.com/download)
1. [Maven](https://maven.apache.org/install.html) 3.8.6

### Checking Installed Versions

```
    java --version
    mvn --version
```

VSCode Extensions:
1. Scala Metals
1. Scala Syntax (official)
1. Maven for Java 

## API Under Test

The API required for testing is [here](https://github.com/fatmanmclone90/wiremock-example.)

## Executing the Test

In terminal:

```
    cd ./Source

    mvn clean -e gatling:test -DbaseUrl="http://localhost:5001" -DdurationSeconds="60" -DnumberOfMessages="500" -DapiKey="wiremock" "-Dgatling.simulationClass=com.example.simulations.LoadTest"
```

In Docker:

```
    cd ./Source

    docker run --rm --name gatling-example -v ${PWD}:/usr/src/mymaven -w /usr/src/mymaven --net wiremock maven:3.8.6-jdk-11 mvn clean -e gatling:test -DbaseUrl="http://wiremock:8080" -DdurationSeconds="10" -DnumberOfMessages="20" -DapiKey="wiremock" "-Dgatling.simulationClass=com.example.simulations.LoadTest"
```
- IF running on Windows use either ${PWD} for Powershell or %cd% for CMD rather than "PWD".
- Assumes the Wiremock container is running on a "wiremock" network
- Assumes the Wiremock container has name "wiremock"

## Building ##

To ensure the project can build:
```
    cd ./Source
    mvn clean
```

## Checking for Package Updates

To ensure the project can build:
```
    cd ./Source
    mvn versions:display-dependency-updates

    mvn versions:display-plugin-updates
```

Suggested changes can be applied in `pom.xml`.

## Test Report

After execution HTML test report can be found at `.\Source\target\gatling\`

See [here](https://gatling.io/docs/gatling/reference/current/stats/reports/) for report description.

## Test Log

Test log can be found at: `Source/gatling.log`.  See `Source/src/test/resources/logback.xml` for configuration.