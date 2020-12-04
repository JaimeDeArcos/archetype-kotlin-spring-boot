import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

	application

	id("org.springframework.boot") version "2.1.13.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"

	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	kotlin("plugin.jpa") version "1.3.61"
	kotlin("kapt") version "1.3.70"

}

val appName = "kotlin-springboot-template"
val appVersion = "0.0.1-SNAPSHOT"
val javaVersion = JavaVersion.VERSION_1_8

version = appVersion
group = "es.jaimedearcos.templates"

application {
	mainClassName = "es.jaimedearcos.templates.ApplicationKt"
	applicationName = appName
}

java {
	sourceCompatibility = javaVersion
	targetCompatibility = javaVersion
}

springBoot {
	buildInfo {
		properties {
			artifact = "$appName.jar"
			version = appVersion
			name = appName
		}
	}
}

repositories {
	mavenLocal()
	mavenCentral()
}

sourceSets {
	getByName("main").java.srcDirs("src/main/kotlin")
	getByName("test").java.srcDirs("src/test/kotlin")
}

dependencies {

	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("mysql:mysql-connector-java")
	implementation("org.flywaydb:flyway-core")
	implementation("com.fasterxml.jackson.core:jackson-databind")

	// Mapstruct
	compile("org.mapstruct:mapstruct:1.3.1.Final")
	api("com.github.pozo:mapstruct-kotlin:1.3.1.1")
	kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
	kapt("com.github.pozo:mapstruct-kotlin-processor:1.3.1.1")

	// Vavr
	compile("io.vavr:vavr:0.9.0")

	// Springfox (swagger)
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")

	// Firebase - notifications
	implementation("com.google.firebase:firebase-admin:6.13.0")

	// Kotlin things
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// --- Test ---
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testCompile("org.projectreactor:reactor-spring:1.0.1.RELEASE")

	testImplementation("io.mockk:mockk:1.9.3")

	// Cucumber
	testImplementation("io.cucumber:cucumber-spring:5.4.1")
	testImplementation("io.cucumber:cucumber-junit:5.4.1")
	testImplementation("io.cucumber:cucumber-java8:5.4.1")
	testImplementation("net.masterthought:cucumber-reporting:4.2.3")

	testCompile ("org.testng:testng:6.13.1")

	// WireMock
	testImplementation("com.github.tomakehurst:wiremock:2.26.1")
	testImplementation("com.h2database:h2")

}

tasks{

	withType<Test> {
//		useJUnitPlatform()
		testLogging.showStandardStreams = true
//		useTestNG {
//			maxParallelForks = 2
//		}
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict", "-include-runtime")
			jvmTarget = "1.8"
		}
	}

	bootJar {

		from("./.ebextensions") {
			into (".ebextensions")
		}

        archiveFileName.set(appName.toLowerCase()+".jar")
	}

}
