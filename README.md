GeckoViewRunner
===============

An example howto to build and run Mozilla's GeckoView on Android.

Preparing
-----
Define the required enviroment variable:

`export JAVA_HOME=/usr/lib/jvm/default`

Download the command line tools at:

https://developer.android.com/studio#command-line-tools-only

Download the gradle build tool from:

https://gradle.org/releases/

https://gradle.org/next-steps/?version=8.4&format=bin

Extract the command line tools and gradle like this:

    GeckoViewRunner
    |
    +--- app/
    |
    +--- cmdline-tools/
    |
    +--- gradle-8.4/

Run:

`./cmdline-tools/latest/bin/sdkmanager "platform-tools" "build-tools;33.0.2" "platforms;android-33"`

After that the structure looks like:

    GeckoViewRunner
    |
    +--- app/
    |
    +--- build-tools/
    |
    +--- cmdline-tools/
    |
    +--- gradle-8.4/
    |
    +--- licenses/
    |
    +--- platform-tools/
    |
    +--- platforms/
    |
    +--- build.gradle
    |
    +--- gradle.properties
    |
    +--- local.properties
    |
    +--- settings.gradle

Check and init gradle:

`./gradle-8.4/bin/gradle -v`

Build the example application:

`./gradle-8.4/bin/gradle build`

Finally you can install the .apk file on a connected device with

`./platform-tools/adb install -r app/build/outputs/apk/debug/app-debug.apk`


Next steps
---------

    keytool -genkeypair -keystore KEYSTORE.FILE -alias KEYALIAS \
         -validity 10000 -keyalg RSA -keysize 2048 \
         -storepass PASSWORD -keypass PASSWORD

    ./gradle-8.4/bin/gradle bundle --rerun \
        -Pandroid.injected.signing.store.file=$KEYSTORE.FILE \
        -Pandroid.injected.signing.store.password=$STOREPASS \
        -Pandroid.injected.signing.key.alias=$KEYALIAS \
        -Pandroid.injected.signing.key.password=$KEYPASS