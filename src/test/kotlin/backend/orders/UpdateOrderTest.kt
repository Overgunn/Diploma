package backend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.OrderErrorResponse.wrongOrderStatus
import backend.api.models.orders.ProductOrderRequest
import backend.api.models.orders.UpdateOrderRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UpdateOrderTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    @DisplayName("Update order status test")
    fun updateOrderStatusCheck() {
        val userToken = authHelper.getNewToken()

       val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()

        val updatedOrder = orders.updateOrderById(
            token = userToken,
            id = order.id,
            body = UpdateOrderRequest(orderStatus = "IN_PROGRESS")
        ).getAsObject()

        updatedOrder.orderStatus shouldBe "IN_PROGRESS"
        updatedOrder.id shouldBe order.id
    }


    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    @ParameterizedTest(name = "Update order status to: {0}")
    @ValueSource(strings = ["PENDING", "IN_PROGRESS", "COMPLETED"])
    fun updateOrderStatusParametrizedCheck(status: String) {
        val userToken = authHelper.getNewToken()

        val order = orders.createNewOrder(
            order = CreateOrderRequest(null, listOf(ProductOrderRequest(1)))
        ).getAsObject()

        val updatedOrder = orders.updateOrderById(
            token = userToken,
            id = order.id,
            body = UpdateOrderRequest(orderStatus = status)
        ).getAsObject()

        updatedOrder.orderStatus shouldBe status
        updatedOrder.id shouldBe order.id
    }

    @Test
    @Tags(Tag("regress"),Tag("backend"),Tag("orders"))
    @DisplayName("Update order with invalid status")
    fun updateNonexistentOrderStatusCheck() {
        val userToken = authHelper.getNewToken()

        val order = orders.createNewOrder(CreateOrderRequest(null, listOf(ProductOrderRequest(1)))).getAsObject()

        val updatedOrder = orders.updateOrderById(
            token = userToken,
            id = order.id,
            body = UpdateOrderRequest(orderStatus = "RANDOM")
        )

        val error = updatedOrder.getErrorAsObject<ErrorResponse>()

        error shouldBe wrongOrderStatus
    }
}
//негативные кейсы(невалидный статус)