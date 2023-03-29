import net.researchgate.release.ReleaseExtension

plugins {
    id("org.springframework.boot") version "2.7.9"
    id("io.spring.dependency-management") version "1.1.0"
    id("net.researchgate.release") version "3.0.2"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.example"
version = requireNotNull(property("version"))
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configure<ReleaseExtension> {
    val releaseVersion = if (hasProperty("release.releaseVersion")) {
        requireNotNull(property("release.releaseVersion"))
    } else {
        version
    }

    ignoredSnapshotDependencies.set(listOf("net.researchgate:gradle-release"))
    pushReleaseVersionBranch.set("release/${ releaseVersion }")
    tagTemplate.set("v${ releaseVersion }")
    newVersionCommitMessage.set("Update next development version after Release")
    with(git) {
        requireBranch.set("master")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
