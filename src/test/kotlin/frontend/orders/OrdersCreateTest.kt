package frontend.orders

import frontend.components.popup.CartPopup
import frontend.components.popup.OrderPopup
import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class OrdersCreateTest: BasicUiHelper() {

    @Test
    @Tags(Tag("create-order"), Tag("frontend"))
    @DisplayName("Create an order from main page and check order popup window")
    fun createOrder() {
        MainPage().open().getPopularProducts().first().btnIncrement.click()

        MainPage().navigateHeader().clickLink("Cart")
        CartPopup().checkoutButtonClick()

        val orderId = OrderPopup().getOrderId()
        OrderPopup().orderPopupCloseBtn()

        MainPage().navigateHeader().clickLink("Order")
        OrdersPage().enterOrderId(orderId)

        val orderedItems = OrdersPage().getOrderItems()

        orderedItems.size.shouldBe(1)
    }
}