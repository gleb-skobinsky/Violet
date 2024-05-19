repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    val jacksonVersion = "2.15.3"
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
}