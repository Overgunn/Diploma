package frontend.components.list

import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Extensions.Companion.toMoney
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step
import kotlin.text.trim

class OrderItems {

    private val orderItems = elements(byDataTestGroup("order-card"))

    @Step("Get order items list")
    fun getItems(): List<OrderItem> = orderItems.map {
                OrderItem(
                    orderId = it.find(byDataTestGroup("order-id")).text
                        .replace("Order ID: ", "")
                        .trim()
                        .toInt(),
                    status = it.find(byDataTestGroup("order-status")).text
                        .replace("Status: ", "")
                        .trim(),
                    createdAt = it.find(byDataTestGroup("order-created")).text,
                    name = it.find(byDataTestGroup("order-product-name")).text,
                    quantity = it.find(byDataTestGroup("order-product-qty")).text
                        .replace("x", "")
                        .trim()
                        .toInt(),
                    unitPrice = it.find(byDataTestGroup("order-product-unit-price")).text.toMoney(),
                    productPrice = it.find(byDataTestGroup("order-product-price")).text.toMoney(),
                    totalPrice = it.find(byDataTestGroup("order-total")).text.toMoney()
                )
            }
    }

data class OrderItem(
    val orderId: Int,
    val status: String,
    val createdAt: String,
    val name: String,
    val quantity: Int,
    val unitPrice: Double,
    val productPrice: Double,
    val totalPrice: Double
)