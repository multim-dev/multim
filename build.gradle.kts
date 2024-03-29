import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.resolve.multiplatform.onlyFromThisModule
import java.util.*

plugins {
    kotlin("jvm") version "1.8.0" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.gitlab.arturbosch.detekt") version("1.22.0")
}



group = "dev.usbharu"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        val props = Properties()
        try {
            props.load(file("${project.rootProject.projectDir}/local.properties").inputStream())
        } catch (e: Exception) {
            println("local.properties not found")
        }
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/multim-dev/kmp-logger")
            credentials {

                username = props.getProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = props.getProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
        maven {
            name = "GitHubPackages2"
            url = uri("https://maven.pkg.github.com/multim-dev/emoji-kt")
            credentials {

                username = props.getProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = props.getProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.serialization")
        plugin("maven-publish")
        plugin("org.jetbrains.dokka")
        plugin("io.gitlab.arturbosch.detekt")
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

    tasks {
        val sourcesJar by creating(Jar::class) {
            archiveClassifier.set("sources")
            from(project.the<SourceSetContainer>()["main"].allSource)
        }
    }


    tasks.getByName("test", Test::class) {
        val props = Properties()
//
        try {
            props.load(file("${project.rootProject.projectDir}/local.properties").inputStream())
            systemProperties("multim_misskey_token" to props["multim_misskey_token"])
            systemProperties("multim_misskey_instance" to props["multim_misskey_instance"])
            systemProperties("multim_misskeyv13_token" to props["multim_misskeyv13_token"])
            systemProperties("multim_misskeyv13_instance" to props["multim_misskeyv13_instance"])

        } catch (e: Exception) {
            println("local.properties not found")
            systemProperties("multim_misskey_token" to System.getenv("multim_misskey_token"))
            systemProperties("multim_misskey_instance" to System.getenv("multim_misskey_instance"))
            systemProperties("multim_misskeyv13_token" to System.getenv("multim_misskeyv13_token"))
            systemProperties("multim_misskeyv13_instance" to System.getenv("multim_misskeyv13_instance"))
        }

        useJUnitPlatform()
    }

    val ktor_version = "2.2.2"
    dependencies {
        "implementation"("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
        "api"("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
        "implementation"("io.ktor:ktor-client-core:$ktor_version")
        "implementation"("io.ktor:ktor-client-cio:$ktor_version")
        "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        "implementation"("io.ktor:ktor-client-content-negotiation:$ktor_version")
        "implementation"("io.ktor:ktor-client-logging:$ktor_version")
        "implementation"("io.ktor:ktor-client-websockets:$ktor_version")
        "implementation"("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
        "implementation"("dev.usbharu:kmp-logger:1.1.1")
        "implementation"("dev.usbharu:emoji-kt:1.1.1")
        "api"("com.michael-bull.kotlin-result:kotlin-result:1.1.16")
        "implementation"("io.github.reactivecircus.cache4k:cache4k:0.9.0")

        "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.9.0")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params:5.9.0")
        "testImplementation"("org.assertj:assertj-core:3.2.0")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.9.0")
        "testImplementation"("io.ktor:ktor-client-mock:$ktor_version")
        "testImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
        "testImplementation"("org.slf4j:slf4j-simple:2.0.4")
        "testImplementation"("io.github.artsok:rerunner-jupiter:2.1.6")
        "implementation"("com.goncalossilva:murmurhash:0.4.0")
        "testImplementation"("io.mockk:mockk:1.13.4")
    }

    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/multim-dev/multim")
                credentials {
                    username =
                        project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
                }
            }
        }
        publications {
            register<MavenPublication>("gpr") {
                groupId = project.group.toString()
                artifactId = "multim-${project.name}"
                version = project.version.toString()
                from(components["kotlin"])
                artifact(tasks["sourcesJar"])
            }
        }
    }

    detekt {
        parallel = true
        buildUponDefaultConfig = true
        basePath = rootDir.absolutePath
        config = files("$rootDir/detekt.yml")
        autoCorrect = true

    }
}
