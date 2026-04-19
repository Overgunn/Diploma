package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Extensions.Companion.toMoney
import frontend.helpers.Wrappers.Companion.byDataTestGroup

class OrderItems(val items: ElementsCollection) {

    private val orderItems = elements(byDataTestGroup("order-card"))

    fun getItems(): List<OrderItem> {
        return orderItems
            .map {
                OrderItem(
                    name = it.find(byDataTestGroup("order-product-name")).text,
                    quantity = it.find(byDataTestGroup("order-product-qty")).text.toInt(),
                    unitPrice = it.find(byDataTestGroup("order-product-unit-price")).text.toMoney(),
                    productPrice = it.find(byDataTestGroup("order-product-price")).text.toMoney(),
                    totalPrice = it.find(byDataTestGroup("order-total")).text.toMoney(),
                )
            }
    }
}

data class OrderItem(
    val name: String,
    val quantity: Int,
    val unitPrice: Double,
    val productPrice: Double,
    val totalPrice: Double
)