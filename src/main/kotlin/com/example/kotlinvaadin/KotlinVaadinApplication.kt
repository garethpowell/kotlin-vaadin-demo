package com.example.kotlinvaadin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [ErrorMvcAutoConfiguration::class])
class KotlinVaadinApplication

fun main(args: Array<String>) {
	runApplication<KotlinVaadinApplication>(*args)
}
