package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.collections.shouldBeIn
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateOrderTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create and get order")
    fun createOrder() {
        val userToken = authHelper.getNewToken()

        val order = orders.createOrder(token = userToken, CreateOrderRequest(null, listOf(ProductOrderRequest(1))))?.getAsObject()
        val allOrders = orders.getOrders().getAsObject()

        order shouldBeIn allOrders
    }
}