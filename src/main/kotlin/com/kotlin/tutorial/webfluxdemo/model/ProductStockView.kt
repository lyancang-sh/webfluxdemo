package com.kotlin.tutorial.webfluxdemo.model

class ProductStockView(product: Product, var stockQuantity: Int) {
    var id: Int = 0
    var name: String = ""
    var price: Float = 0.0f

    init {
        this.id = product.id
        this.name = product.name
        this.price = product.price
    }
}

data class Product(
        var id: Int = 0,
        var name: String = "",
        var price: Float = 0.0f
)