package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.collections.shouldNotBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class DeleteOrderTest : Controllers() {

    @Test
    @DisplayName("Create and delete order")
    @Tags(Tag("regress"), Tag("backend"), Tag("orders"))
    fun deleteOrderCheck() {
        val userToken = AuthorizationHelper().getNewToken()

        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val delete = orders.deleteOrder(token = userToken, order.id)
        val allOrders = orders.getAllOrders().getAsObject()

        delete.code() shouldBe 200
        order shouldNotBeIn allOrders
    }

    @Test
    @DisplayName("Delete non-existent order")
    @Tags(Tag("regress"), Tag("backend"), Tag("orders"))
    fun deleteNonexistentOrder() {
        val userToken = AuthorizationHelper().getNewToken()
        val delete = orders.deleteOrder(token = userToken, 0)

        delete.code() shouldBe 404
        delete.message() shouldBe "Not Found"
    }
}