
# AltaPay - JavaSDK

For integrating Java projects with the AltaPay gateway.

## How to Build

1. Create local `gradle.properties` file in the root of the project with the following content:

    ```properties
    usr=USER
    pwd=PASSWORD
    ```
    
    Replace `USER` and `PASSWORD` with your credentials in case you're willing to publish the artifact to the Maven repository.

2. Run the following command to build the project:

    ```shell
    ./gradlew clean build
    ```
   
3. To update the SDK with the latest payment gateway changes, please replace the `src/main/xsd/APIResponse.xsd` file with the newest version and run:

    ```shell
    ./gradlew clean xjc build
    ```

4. To publish the artifact to the local Maven repository, first comment-out:

   ```gradle
   signing {
       sign publishing.publications.mavenJava
   }
   ```
   
   then run:

    ```shell
    ./gradlew publishToMavenLocal
    ```

    The artifact will be published to the local Maven repository.

## Dependency

### Maven

    <dependency>
        <groupId>com.altapay</groupId>
        <artifactId>sdk-java</artifactId>
        <version>3.1.7</version>
    </dependency>

### Gradle

    implementation 'com.altapay:sdk-java:3.1.7'

## Changelog

See [Changelog](CHANGELOG.md) for all the release notes.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.

## Documentation

For more details please see [AltaPay docs](https://documentation.altapay.com/)
