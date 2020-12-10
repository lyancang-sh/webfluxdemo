package com.kotlin.tutorial.webfluxdemo.config

import com.kotlin.tutorial.webfluxdemo.controller.ProductsHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @FlowPreview
    @Bean
    fun productRoutes(productsHandler: ProductsHandler) = coRouter {
        GET("/router/product", productsHandler::findOneInStock)
    }
}