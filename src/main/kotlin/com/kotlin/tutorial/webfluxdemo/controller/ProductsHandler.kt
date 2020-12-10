package com.kotlin.tutorial.webfluxdemo.controller

import com.kotlin.tutorial.webfluxdemo.model.Product
import com.kotlin.tutorial.webfluxdemo.model.ProductStockView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.server.*

@Component
class ProductsHandler(
        @Autowired var webClient: WebClient) {

    /*
    http://localhost:8080/router/product
     */
    suspend fun findOneInStock(request: ServerRequest): ServerResponse {
        val quantity: Deferred<Int> = GlobalScope.async {
            webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().awaitBody<Int>()
        }
        return ServerResponse.ok().json().bodyValueAndAwait(ProductStockView(Product(name = "router"), quantity.await()))
    }
}
