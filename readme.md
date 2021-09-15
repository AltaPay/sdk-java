
# JavaSDK - for integrating Java projects with the AltaPay gateway.

## Change Log

### 1.0.0

- Supports API changes from 20210324
- Add support to agreements using agreement_type

## How to build

To update the SDK with the latest payment gateway changes run below command from the root of the project

    $ ant DistZip

## Dependency

### Maven

    <dependency>
        <groupId>com.altapay</groupId>
        <artifactId>sdk-java</artifactId>
        <version>1.0.0</version>
    </dependency>

### Gradle

    implementation 'com.altapay:sdk-java:1.0.0'