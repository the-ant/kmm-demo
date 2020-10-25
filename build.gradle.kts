buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    val kotlinVersion = "1.4.10"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.1.0")
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}
group = "com.sdt.kmm.demoapp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
