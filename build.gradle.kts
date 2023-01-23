import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties


plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


tasks.test {
    val props = Properties()
//
    try {
        props.load(file("local.properties").inputStream())
        systemProperties("multim_misskey_token" to props["multim_misskey_token"])

    } catch (e: Exception) {
        e.printStackTrace()
    }

    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//import org.jetbrains.kotlin.konan.properties.Properties
//
//plugins {
//    kotlin("jvm") version "1.8.0"
//    kotlin("plugin.serialization") version "1.8.0"
//    id("application")
//}
//
//group = "dev.usbharu"
//version = "1.0-SNAPSHOT"
//
val ktor_version = "2.2.2"
//
//repositories {
//    mavenCentral()
//}
//
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.assertj:assertj-core:3.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("io.ktor:ktor-client-mock:$ktor_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
//    implementation(kotlin("stdlib"))
    testImplementation("org.slf4j:slf4j-simple:2.0.4")
}
//
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "11"
//}
//
//tasks.test {
//    val props = Properties()
//
//    val token: String = try {
//        props.load(file("local.properties").inputStream())
//        props["multim_misskey_token"].toString()
//    } catch (e: Exception) {
//        e.printStackTrace()
//        System.getenv("multim_misskey_token")
//    }
//    System.setProperties(props)
//
//    useJUnitPlatform()
//}

//tasks.register("generateBuildKonfig")


//
//kotlin {
//    android("android") {
//        publishLibraryVariants("release")
//    }
//
//
//    jvm {
//        compilations.all {
//            kotlinOptions.jvmTarget = "1.8"
//        }
////        withJava()
//        testRuns["test"].executionTask.configure {
//            useJUnitPlatform()
//        }
//    }
//    js(IR) {
//        useCommonJs()
//        browser {
//
//        }
//        nodejs {
//
//        }
//        binaries.library()
//    }
//
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
//                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
//                implementation("io.ktor:ktor-client-core:$ktor_version")
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
//                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
//                implementation("io.ktor:ktor-client-logging:$ktor_version")
//                implementation("io.ktor:ktor-client-websockets:$ktor_version")
//            }
//        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test"))
//                implementation(kotlin("test-common"))
//                implementation(kotlin("test-annotations-common"))
//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
//                implementation("io.ktor:ktor-client-mock:$ktor_version")
//            }
//        }
//        val jsMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-js:$ktor_version")
//            }
//        }
//        val jsTest by getting {
//            dependsOn(commonTest)
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }
//        val jvmMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-cio:$ktor_version")
//            }
//        }
//        val jvmTest by getting {
//            dependencies {
//                implementation("io.ktor:ktor-server-cio:$ktor_version")
//                implementation("io.ktor:ktor-server-core:$ktor_version")
//                implementation("io.ktor:ktor-server-websockets:$ktor_version")
//            }
//        }
//        val androidMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
//            }
//        }
//    }
//}
//
//android {
//    compileSdk = 32
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
//    sourceSets["main"].java.srcDirs("src/androidMain/kotlin")
//    sourceSets["main"].res.srcDirs("src/androidMain/res")
//    defaultConfig {
//        minSdk = 26
//        targetSdk = 32
//    }
//
//
//    afterEvaluate {
//        project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()
//            ?.let { ext ->
//                ext.sourceSets.removeAll { sourceSet ->
//                    setOf(
////                    "androidAndroidTestRelease",
//                        "androidTestFixtures",
//                        "androidTestFixturesDebug",
//                        "androidTestFixturesRelease",
//                    ).contains(sourceSet.name)
//                }
//            }
//    }
//
//}
//
//buildkonfig {
//
//    val props = Properties()
//
//    val token: String = try {
//        props.load(file("local.properties").inputStream())
//        props["multim_misskey_token"].toString()
//    } catch (e: Exception) {
//        e.printStackTrace()
//        System.getenv("multim_misskey_token")
//    }
//
//    packageName = "dev.usbharu.multim.secret"
//
//    defaultConfigs {
//        buildConfigField(STRING, "token", token)
//    }
//
////    targetConfigs {
////        create("commoonTest") {
////            buildConfigField(STRING,"token",props["multim_misskey_token"].toString() )
////        }
////    }
//}
