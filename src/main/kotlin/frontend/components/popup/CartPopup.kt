package frontend.components.popup

import com.codeborne.selenide.Selenide.element
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class CartPopup {
    private val cartPopupTotalSum get() = element(byDataTestId("cart-total-price"))
    private val cartCheckoutButton get() = element(byDataTestId("cart-checkout"))

    @Step("Check sum value in popup window")
    fun cartShouldHaveTotalSum(): String {
        return cartPopupTotalSum.text
    }

    @Step("Checkout button")
    fun checkoutButtonClick(): OrderPopup {
        cartCheckoutButton.click()
        return OrderPopup()
    }

    @Step("Checkout button text check")
    fun cartButtonText(): String {
        return cartCheckoutButton.text
    }

    @Step("Get cart total price")
    fun getTotalPrice(): Float {
        return cartPopupTotalSum.text.filter {it.isDigit()}.toFloat() / 100f
    }
}