plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    id("io.quarkus")
    id("java-library")
    id("net.afanasev.sekret") version "0.1.2"
    id("maven-publish")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.kbalazsworks"
            artifactId = "stackjudge_aws"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}

allprojects {
    repositories {
        maven("https://jitpack.io")
    }
}

dependencies {
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
    implementation("io.quarkus:quarkus-arc:2.4.1.Final")
    implementation("org.projectlombok:lombok:1.18.20")
    implementation("net.afanasev:sekret-compiler:0.1.0")
    implementation("net.afanasev:sekret-annotation:0.1.2")
    implementation("net.afanasev:sekret-kotlin-plugin:0.1.2")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    // custom
    implementation("com.github.balazskrizsan:simple_oidc:c0213f36d7d41d97c7391d43f920d5b4756715c2")
    // https://mvnrepository.com/artifact/org.eclipse.microprofile.rest.client/microprofile-rest-client-api
    implementation("org.eclipse.microprofile.rest.client:microprofile-rest-client-api:3.0")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-ses
    implementation("com.amazonaws:aws-java-sdk-ses:1.12.181")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-s3
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.181")
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-resteasy-reactive
    implementation("io.quarkus:quarkus-resteasy-reactive:2.4.2.Final")
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-resteasy-reactive-qute
    implementation("io.quarkus:quarkus-resteasy-reactive-qute:2.4.2.Final")
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-smallrye-openapi
    implementation("io.quarkus:quarkus-smallrye-openapi:2.5.0.CR1")
    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    // https://mvnrepository.com/artifact/io.quarkus/quarkus-resteasy-reactive-jsonb
    implementation("io.quarkus:quarkus-resteasy-reactive-jsonb:2.7.2.Final")
    // https://mvnrepository.com/artifact/io.github.microutils/kotlin-logging-jvm
    runtimeOnly("io.github.microutils:kotlin-logging-jvm:2.1.21")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.22.0")
    // https://mvnrepository.com/artifact/org.mockito.kotlin/mockito-kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
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

sekret {
    // "■■■" by default
    mask = "**masked-value**"

    // true by default
    enabled = true

    // true by default
    maskNulls = false
}