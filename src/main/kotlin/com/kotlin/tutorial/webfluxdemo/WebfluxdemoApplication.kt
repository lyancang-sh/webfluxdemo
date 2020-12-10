package com.kotlin.tutorial.webfluxdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication(scanBasePackages = arrayOf("com.kotlin.tutorial.webfluxdemo"), exclude = arrayOf(SecurityAutoConfiguration::class))
class WebfluxdemoApplication

fun main(args: Array<String>) {
	runApplication<WebfluxdemoApplication>(*args)
}
