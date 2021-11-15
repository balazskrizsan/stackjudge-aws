plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk
    implementation("com.amazonaws:aws-java-sdk:1.11.923")
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-resteasy-reactive
    implementation("io.quarkus:quarkus-resteasy-reactive:2.4.2.Final")
    // https://mvnrepository.com/artifact/io.pebbletemplates/pebble
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-resteasy-reactive-qute
    implementation("io.quarkus:quarkus-resteasy-reactive-qute:2.4.2.Final")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-maven-lombok
    implementation("org.jetbrains.kotlin:kotlin-maven-lombok:1.6.0-RC2")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
    implementation("io.quarkus:quarkus-arc:2.4.1.Final")
    implementation("org.projectlombok:lombok:1.18.20")
    // implementation("io.quarkus:quarkus-resteasy")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "com.kbalazsworks"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
