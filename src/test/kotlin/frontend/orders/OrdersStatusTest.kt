package frontend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.UpdateOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.OrderHelperBE
import frontend.helpers.TestBaseUI
import frontend.pages.OrdersPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class OrdersStatusTest: TestBaseUI() {

    val orderHelper = OrderHelperBE()
    val controllers = Controllers()
    val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create, and check order status via UI")
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    fun orderStatusCheck() {
        val testOrder = orderHelper.createOrderWithRandomProduct()

        val orderCheck = OrdersPage()
            .open()
            .enterOrderId(testOrder.id)
            .getOrderItems()
            .first { it.orderId == testOrder.id }

        orderCheck shouldNotBe null
        orderCheck.orderId shouldBe testOrder.id
        orderCheck.status shouldBe "PENDING"
    }

    @Test
    @DisplayName("Create and update order status, check updated status via UI")
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    fun orderStatusUpdateCheck() {

        val userToken = authHelper.getNewToken()
        val testOrder = orderHelper.createOrderWithRandomProduct()

        testOrder.orderStatus shouldBe "PENDING"

        val updatedOrder = controllers.orders.updateOrderById(
            token = userToken,
            id = testOrder.id,
            body = UpdateOrderRequest(orderStatus = "IN_PROGRESS")
        ).getAsObject()

        updatedOrder.orderStatus shouldNotBe "PENDING"

        val orderUpdate = OrdersPage()
            .open()
            .enterOrderId(updatedOrder.id)
            .getOrderItems()
            .first { it.orderId == updatedOrder.id }

        orderUpdate shouldNotBe null
        orderUpdate.orderId shouldBe testOrder.id
        orderUpdate.status shouldBe "IN_PROGRESS"
    }
}