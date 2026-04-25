package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import io.kotest.matchers.collections.shouldBeIn
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class CreateOrderTest: Controllers() {

    @Test
    @Tags(Tag("create-order"),Tag("backend"))
    @DisplayName("Create and get order")
    fun createOrder() {

        val order = orders.createOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val allOrders = orders.getOrders().getAsObject()

        order shouldBeIn allOrders
    }
}