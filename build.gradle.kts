plugins {
    kotlin("jvm") version "2.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.baggi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.github.kotlin-telegram-bot.kotlin-telegram-bot:dispatcher:6.2.0")
}

kotlin {
    jvmToolchain(21)
}