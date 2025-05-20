plugins {
	java
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// springboot
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	developmentOnly ("org.springframework.boot:spring-boot-devtools")

	// security
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.1.1.RELEASE")

	// lombok
	annotationProcessor ("org.projectlombok:lombok")
	compileOnly ("org.projectlombok:lombok")

	//thymeleaf
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation ("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")


	//DB
	runtimeOnly ("com.h2database:h2")
	runtimeOnly ("com.mysql:mysql-connector-j")

	//ModelMapper
	implementation("org.modelmapper:modelmapper:3.1.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}