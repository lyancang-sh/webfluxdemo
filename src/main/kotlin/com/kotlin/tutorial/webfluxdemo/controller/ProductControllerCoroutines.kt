package com.kotlin.tutorial.webfluxdemo.controller

import com.kotlin.tutorial.webfluxdemo.model.Product
import com.kotlin.tutorial.webfluxdemo.model.ProductStockView
import com.kotlin.tutorial.webfluxdemo.utils.logger
import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@RestController
class ProductControllerCoroutines {
    var log = logger()

    @Autowired
    lateinit var webClient: WebClient

    /*
    http://localhost:8080/coroutine/product
     */
    @GetMapping("/coroutine/product")
    suspend fun findProduct(): ProductStockView = coroutineScope {

        val quantity: Deferred<Int> = async(start = CoroutineStart.LAZY) {
            log.info("call webclient quantity current scope...")
            webClient.get()
                    .accept(APPLICATION_JSON)
                    .retrieve().awaitBody<Int>()
        }
        val coroutineFromSpecifiedScope: Deferred<Int> = GlobalScope.async {
            log.info("call webclient quantity2...")
            webClient.get()
                    .accept(APPLICATION_JSON)
                    .retrieve().awaitBody<Int>()
        }
        ProductStockView(Product(name = "coroutine"), quantity.await())
    }
}