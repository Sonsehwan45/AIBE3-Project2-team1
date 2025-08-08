plugins {
    id("java")
    id("application")
}

group = "com.ll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("com.ll.Main")
}

tasks.test {
    useJUnitPlatform()
}

// 모든 Java 관련 태스크에 인코딩 설정
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dfile.encoding=UTF-8", "-Dconsole.encoding=UTF-8")
}

// Gradle 자체 인코딩
System.setProperty("file.encoding", "UTF-8")