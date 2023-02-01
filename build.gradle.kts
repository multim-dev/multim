import org.jetbrains.kotlin.gradle.plugin.sources.getVisibleSourceSetsFromAssociateCompilations
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    kotlin("jvm") version "1.8.0" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false
    id("maven-publish")
}



group = "dev.usbharu"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.serialization")
        plugin("maven-publish")
    }

    group = rootProject.group
    version = rootProject.version

    val compileKotlin: KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }

    val compileTestKotlin: KotlinCompile by tasks
    compileTestKotlin.kotlinOptions {
        jvmTarget = "1.8"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }



    tasks.getByName("test", Test::class) {
        val props = Properties()
//
        try {
            props.load(file("${project.rootProject.projectDir}/local.properties").inputStream())
            systemProperties("multim_misskey_token" to props["multim_misskey_token"])
            systemProperties("multim_misskey_instance" to props["multim_misskey_instance"])

        } catch (e: Exception) {
            e.printStackTrace()
            systemProperties("multim_misskey_token" to System.getenv("multim_misskey_token"))
            systemProperties("multim_misskey_instance" to System.getenv("multim_misskey_instance"))
        }

        useJUnitPlatform()
    }

    val ktor_version = "2.2.2"
    dependencies {
        "implementation"("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
        "implementation"("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
        "implementation"("io.ktor:ktor-client-core:$ktor_version")
        "implementation"("io.ktor:ktor-client-cio:$ktor_version")
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        "implementation"("io.ktor:ktor-client-content-negotiation:$ktor_version")
        "implementation"("io.ktor:ktor-client-logging:$ktor_version")
        "implementation"("io.ktor:ktor-client-websockets:$ktor_version")
        "implementation"("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")


        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.9.0")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params:5.9.0")
        "testImplementation"("org.assertj:assertj-core:3.2.0")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.9.0")
        "testImplementation"("io.ktor:ktor-client-mock:$ktor_version")
        "testImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        "testImplementation"("org.slf4j:slf4j-simple:2.0.4")
        "testImplementation"("io.github.artsok:rerunner-jupiter:2.1.6")
    }
}
