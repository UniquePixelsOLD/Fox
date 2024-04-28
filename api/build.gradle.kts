plugins {
    id("java")
    id("maven-publish")
}

group = "de.dasshorty"
version = "1.0.0"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.uniquepixels"
            artifactId = "fox-api"
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

dependencies {
}