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
    implementation(group = "com.google.inject.extensions", name = "guice-assistedinject", version = "5.0.1")

    // Netty
    implementation(group = "io.netty", name = "netty-all", version = "4.1.72.Final")

    // Apache CLI
    implementation(group = "commons-cli", name = "commons-cli", version = "1.5.0")
}

tasks.jar {
    destinationDirectory.set(file(System.getenv("DELTAN_SERVER_PATH")))

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    manifest {
        attributes["Main-Class"] = "org.deltan.deltan.Main"
    }

    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
