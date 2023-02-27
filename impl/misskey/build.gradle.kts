dependencies {
    implementation(project(":core"))
}

sourceSets {
    test {
        kotlin {
            compileClasspath += project(":core").sourceSets.test.get().output
            runtimeClasspath += project(":core").sourceSets.test.get().output
        }
    }
}

kotlin {

}

//tasks{
//    val sourcesJar by creating(Jar::class) {
//        archiveClassifier.set("sources")
//        from(sourceSets["main"].allSource)
//    }
//}
//
//afterEvaluate {
//    publishing {
//        publications {
//
//
//            create<MavenPublication>("maven") {
//                groupId = project.group.toString()
//                artifactId = "multim-${project.name}"
//                version = project.version.toString()
//                from(components["kotlin"])
//                artifact(tasks["sourcesJar"])
//            }
//
//
//        }
//    }
//}
