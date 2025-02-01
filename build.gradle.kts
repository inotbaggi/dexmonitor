plugins {
    application
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    id("eu.vendeli.telegram-bot") version "7.8.0"
}

group = "me.baggi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("me.baggi.dex.AppKt")
}