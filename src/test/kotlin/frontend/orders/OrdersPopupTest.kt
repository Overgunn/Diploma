package frontend.orders

import frontend.components.popup.CartPopup
import frontend.components.popup.OrderPopup
import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class OrdersPopupTest: BasicUiHelper() {

    @Test
    @Tags(Tag("create-order"), Tag("frontend"))
    @DisplayName("Create an order from main page and check order popup window")
    fun ordersCheckPopupWindow() {
        val firstPopularItem = MainPage().open().getPopularProducts().first()
        firstPopularItem.btnIncrement.click()

        MainPage().navigateHeader().clickLink("Cart")
        CartPopup().checkoutButtonClick()

        OrderPopup().orderPopupTitle() shouldBe "Order Created!"
        OrderPopup().orderPopupWindowStatus() shouldBe "Status: PENDING"
    }
}