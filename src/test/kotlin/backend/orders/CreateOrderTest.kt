package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.ErrorResponse
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.orders.OrderErrorResponse.invalidOrderProduct
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.ProductOrderRequest
import backend.controllers.Controllers
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class CreateOrderTest: Controllers() {

    @Test
    @DisplayName("Create and get order")
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    fun createOrder() {

        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val allOrders = orders.getAllOrders().getAsObject()

        order shouldBeIn allOrders
    }

    @Test
    @DisplayName("Get an order by id")
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    fun getOrderByIdCheck() {
        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val createdOrder = orders.getOrdersById(id = order.id).getAsObject()

        createdOrder shouldBeEqual order
    }

    @Test
    @DisplayName("Create and get order from an offset and limit")
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    fun getOrderFromOffsetAndLimit() {
        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()
        val page1 = orders.getAllOrders(offset = 0, limit = 50).getAsObject()

        page1 shouldContain order
    }

    @Test
    @DisplayName("Get non existing order from all orders in 0 offset")
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    fun getNonExistingOrder() {
        val allOrders = orders.getAllOrders().getAsObject()

        allOrders.shouldNotContain(0)
    }

    @Test
    @DisplayName("Create an order with non-existing product and get error")
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    fun createOrderWithNonExistingProduct() {

    val response = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(0))))
    val error = response.getErrorAsObject<ErrorResponse>()

    error shouldBe invalidOrderProduct
    }
}