package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class DeleteOrderTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    @DisplayName("Create and delete order")
    fun createOrder() {

        val userToken = authHelper.getNewToken()

        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val delete = orders.deleteOrder(token = userToken, order.id)
        delete.code() shouldBe 200
    }
}