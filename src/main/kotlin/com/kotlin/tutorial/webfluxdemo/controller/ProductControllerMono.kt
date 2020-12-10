package com.kotlin.tutorial.webfluxdemo.controller

import com.kotlin.tutorial.webfluxdemo.model.Product
import com.kotlin.tutorial.webfluxdemo.model.ProductStockView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@RestController
class ProductControllerMono {
    @Autowired
    lateinit var webClient: WebClient

    /*
    http://localhost:8080/reactor/product
     */
    @GetMapping("/reactor/product")
    fun findOne(): Mono<ProductStockView> {

        val stockQuantity = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono<Int>()

        return Mono.just(Product(name = "reactor"))
                .zipWith(stockQuantity) { productInStock, stockQty ->
                    ProductStockView(productInStock, stockQty)
                }
    }

    @GetMapping("/getInt")
    fun getInt(): Mono<Int> {
        return Mono.just(4)
    }
}