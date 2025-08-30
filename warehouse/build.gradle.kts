import java.util.Date

plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.iuri"
version = "0.0.1-SNAPSHOT"
description = "Warehouse dio"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct-processor
    implementation("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")

	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")

	annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named("build"){
    doLast {
        val trigger = file("src/main/resources/trigger.txt")
        if(!trigger.exists()){
            trigger.createNewFile()
        }
        trigger.writeText(Date().time.toString())
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}
