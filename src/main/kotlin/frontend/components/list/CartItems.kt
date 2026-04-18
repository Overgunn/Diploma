package frontend.components.list

import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Extensions.Companion.toMoney

class CartItems {

    private val cartItems = elements(byDataTestGroup("cart-item"))

    fun getItems(): List<CartItem> {
        return cartItems
            .map {
                CartItem(
                    image = it.find(byDataTestGroup("cart-item-image")),
                    name = it.find(byDataTestGroup("cart-item-name")).text,
                    price = it.find(byDataTestGroup("cart-item-unit-price")).text.toMoney(),
                    totalPrice = it.find(byDataTestGroup("cart-item-price")).text.toMoney(),
                    btnIncrement = it.find(byDataTestGroup("cart-item-increment")),
                    btnDecrement = it.find(byDataTestGroup("cart-item-decrement")),
                    btnRemoveItem = it.find(byDataTestGroup("cart-item-remove")),
                    quantity = it.find(byDataTestGroup("cart-item-qty")).text.toInt()
                ) }
    }
    data class CartItem(
        val image: SelenideElement,
        val name: String,
        val price: Double,
        val totalPrice: Double,
        val btnIncrement: SelenideElement,
        val btnDecrement: SelenideElement,
        val btnRemoveItem: SelenideElement,
        var quantity: Int
    )
}

