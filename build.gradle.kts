import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

// Dependency Versions
val vaadinVersion = "14.0.9"
val testContainersVersion = "1.12.2"

dependencies {
	implementation("org.springframework.boot", "spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.vaadin:vaadin-bom:$vaadinVersion")
	implementation("com.vaadin:vaadin-spring-boot-starter:$vaadinVersion")
	implementation("io.github.microutils", "kotlin-logging", "1.6.26")
	runtimeOnly("mysql:mysql-connector-java")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.testcontainers", "testcontainers", testContainersVersion)
	implementation("org.testcontainers", "mysql", testContainersVersion)
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
