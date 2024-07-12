plugins {
    kotlin("jvm") version "1.5.21"
    id("org.springframework.boot") version "2.5.2" // 直接書いても問題ない
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.versions.guava)
    testImplementation(libs.versions.junit)
}
