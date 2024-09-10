# Gatling Load Test

This project uses [Gatling](https://gatling.io/) to perform load testing on your application. Gatling is a high-performance Scala-based load testing framework that provides easy-to-understand reports.

## Prerequisites

Before you start, ensure you have the following installed:

- **Java 8+**: Gatling requires Java to run.
- **Maven** or **SBT**: For project build and dependency management.
- **Scala**: For running the Gatling simulations if you're using Scala-based scripts.

## Project Structure

```bash
├── src
│   ├── test
│   │   ├── scala             # Your Gatling test scenarios in Scala
│   │   └── resources         # Configuration files like gatling.conf
├── target                    # Generated reports and results
├── pom.xml                   # Maven configuration (or build.sbt for SBT)
├── gatling.conf              # Gatling configuration file
└── README.md                 # This README file
```

## Run
### Setup
```bash
make setup
```

### Test on Local env
```bash
make local

```

### Test on QA env
```bash
make qa

```

### Clean build version
```bash
make clean

```
