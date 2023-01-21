pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:7.2.2")
            }
        }
    }
}

rootProject.name = "multim"
