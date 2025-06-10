# HelloWorld Android Application

This project contains a sample Android app written mainly in Java. A recent commit added basic Kotlin support and some skeleton classes for an expense tracking feature.

## Building

1. Install **JDK 11** and make sure it is the default `java` on your system. Older Gradle versions used in this project are incompatible with newer JDKs.
2. Install the Android SDK and set the `ANDROID_SDK_ROOT` environment variable or create a `local.properties` file with a `sdk.dir` pointing to your SDK path.
3. Run `./gradlew assembleDebug` to compile the project.

The project currently uses Gradle 6.1.1 with Android Gradle Plugin 4.0.1 and Kotlin 1.4.32.

A sample `local.properties.example` file is provided. Copy it to `local.properties` and update the SDK path.
