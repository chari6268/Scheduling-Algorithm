plugins {
    id("java")
}

group = "com.chari"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.apache.pdfbox:pdfbox:2.0.27") // For PDF files
}

tasks.test {
    useJUnitPlatform()
}