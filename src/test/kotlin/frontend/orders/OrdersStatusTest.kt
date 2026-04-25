package frontend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.UpdateOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.OrderHelper
import frontend.helpers.BasicUiHelper
import frontend.pages.OrdersPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class OrdersStatusTest: BasicUiHelper() {

    val orderHelper = OrderHelper()
    val controllers = Controllers()
    val authHelper = AuthorizationHelper()

    @Test
    @Tags(Tag("create-order"),Tag("orders-page"),Tag("frontend"))
    @DisplayName("Create, and check order status via UI")
    fun orderStatusCheck() {

        val testOrder = orderHelper.createOrderWithRandomProduct()

        val orderCheck = OrdersPage()
            .open()
            .enterOrderId(testOrder.id)
            .getOrderItems()
            .firstOrNull { it.orderId == testOrder.id }

        orderCheck shouldNotBe null
        orderCheck?.orderId shouldBe testOrder.id
        orderCheck?.status shouldBe "PENDING"
    }

    @Test
    @Tags(Tag("create-order"),Tag("orders-page"),Tag("frontend"))
    @DisplayName("Create, and update order status, and check status via UI")
    fun orderStatusUpdateCheck() {

        val userToken = authHelper.getNewToken()
        val testOrder = orderHelper.createOrderWithRandomProduct()

        val updatedOrder = controllers.orders.updateOrderById(
            token = userToken,
            id = testOrder.id,
            body = UpdateOrderRequest(orderStatus = "IN_PROGRESS")
        ).getAsObject()

        val orderUpdate = OrdersPage()
            .open()
            .enterOrderId(updatedOrder.id)
            .getOrderItems()
            .firstOrNull { it.orderId == updatedOrder.id }

        orderUpdate shouldNotBe null
        orderUpdate?.orderId shouldBe testOrder.id
        orderUpdate?.status shouldBe "IN_PROGRESS"
    }
}