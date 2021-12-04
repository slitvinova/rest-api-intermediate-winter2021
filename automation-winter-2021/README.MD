# API automation framework
e2e test runner with Serenity and Gradle

## Getting Started

### Prerequisites

Software to clone this repo (e.g. Git installed locally, SourceTree ...)
Java 8
Java friendly IDE (e.g. IntelliJ IDEA)

### Installing

Clone this repo
Import as gradle project

## Running the tests 

1. Use following task to execute tests: 
`clean test aggregate`
2. Add following arguments for the task:
`-Dcucumber.options="--tags '@run'"` where @run is tag of the test to execute
2.  The test results are located inside `/target/site/serenity/index.html`