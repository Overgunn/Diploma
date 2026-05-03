package backend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import io.qameta.allure.Step

class OrderHelperBE: Controllers() {

    private val productHelper = ProductHelper()

    @Step("Create order with random product")
    fun createOrderWithRandomProduct(): CreateOrderResponse {
        val product = productHelper.createRandomProduct()
        return orders.createNewOrder(
            order = CreateOrderRequest(
                userId = null,
                products = listOf(ProductOrderRequest(product.id))
            )
        ).getAsObject()
    }
}