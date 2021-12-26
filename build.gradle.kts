plugins {
    java
}

group = "org.deltan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Guice
    implementation(group = "com.google.inject", name = "guice", version = "5.0.1")
}

tasks.jar {
    destinationDirectory.set(file(System.getenv("DELTAN_SERVER_PATH")))

    manifest {
        attributes["Main-Class"] = "org.deltan.deltan.Main"
    }

    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
