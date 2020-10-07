package es.spring.microservices.kotlinmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<KotlinMicroserviceApplication>(*args)
}
