package backend.controllers

import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.products.UpdateProductRequest
import backend.helpers.AuthorizationHelper
import io.qameta.allure.Step
import org.example.kotlin.backend.api.endpoints.Endpoints
import retrofit2.Response

class OrderController: Endpoints() {

    val authHelper = AuthorizationHelper()

    @Step("Get all orders")
    fun getOrders(token: String = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 50): Response<List<CreateOrderResponse>> {
        return orders.getOrder(token,offset,limit).execute()
    }

    @Step("Get order with id: {id}")
    fun getOrdersById(id: Any): Response<CreateOrderResponse?>? {
        return orders.getOrderById(id).execute()
    }

    @Step("Get order with user id: {id}")
    fun getOrdersByUserId(id: Any): Response<List<CreateOrderResponse>> {
        return orders.getOrderByUserId(id).execute()
    }

    @Step("Create a new order")
    fun createOrder(
        token: String = authHelper.getAdminToken(),
        order: CreateOrderRequest
    ): Response<CreateOrderResponse?>? {
        return orders.createOrder(token, order).execute()
    }

    @Step("Update order status")
    fun updateOrder(
        token: String = authHelper.getAdminToken(),
        id: Int,
        body: UpdateProductRequest
    ): Response<CreateOrderResponse?>? {
        return orders.updateOrderStatus(token, id, body).execute()
    }
}