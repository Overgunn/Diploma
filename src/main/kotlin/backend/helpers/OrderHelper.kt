package backend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import io.qameta.allure.Step

class OrderHelper: Controllers() {

    @Step
    fun createOrder(count: Int): List<CreateOrderRequest>{
        val listOfOrders = mutableListOf<CreateOrderRequest>()
        for (i in 1.. count) {
            listOfOrders.add(CreateOrderRequest(null, listOf(ProductOrderRequest(1))))
        }

        listOfOrders.forEach {
            orders.createOrder(order = it)
        }
        return listOfOrders.toList()
    }

    private val productHelper = ProductHelper()

    @Step("Create order with random product")
    fun createOrderWithRandomProduct(): CreateOrderResponse {
        val product = productHelper.createRandomProduct()
        return orders.createOrder(
            order = CreateOrderRequest(
                userId = null,
                products = listOf(ProductOrderRequest(product.id))
            )
        ).getAsObject()
    }
}