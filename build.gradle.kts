import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include

plugins {
    id("java")
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "1.6.0"
    id("com.github.johnrengelman.shadow") version ("8.1.1")
    id("xyz.jpenilla.run-paper") version "2.2.4"
}

group = "de.dasshorty"
version = "1.0-SNAPSHOT"


java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 21 on systems that only have JDK 11 installed for example.
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {

    compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")

    paperweight.paperDevBundle("1.20.5-R0.1-SNAPSHOT")

    implementation("org.mongodb:mongodb-driver-sync:4.10.1")

    // Javalin
    implementation("io.javalin:javalin:5.6.3")

    compileOnly("dev.s7a:base64-itemstack:1.0.0")

    // OkHttp Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // https://square.github.io/okhttp/
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.uniquepixels"
            artifactId = "fox"
            version = this.version

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "UniquePixels"
            url = uri("https://repo.uniquepixels.net/repository/minecraft")
            credentials {
                username = "projectwizard"
                password = System.getenv("UP_NEXUS_PASSWORD")
            }
        }
    }
}

tasks.create("generateTemplate") {

}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

    shadowJar {
        dependencies {
            //include(dependency("net.uniquepixels:core-api:1.0.2"))
            include(dependency("com.squareup.okhttp3:okhttp:4.12.0"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib:1.9.10"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib.common:1.9.10"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib.jdk7:1.9.10"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib.jdk8:1.9.10"))
            include(dependency("com.squareup.okio:okio-jvm:3.6.0"))
        }
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(21)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }


    reobfJar {
        // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
        // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
        outputJar.set(layout.buildDirectory.file("dist/Fox-${project.version}.jar"))
    }
}