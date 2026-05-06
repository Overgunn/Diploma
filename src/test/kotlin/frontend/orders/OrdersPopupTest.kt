package frontend.orders

import frontend.components.popup.CartPopup
import frontend.components.popup.OrderPopup
import frontend.helpers.OrderHelperFE
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class OrdersPopupTest : TestBaseUI() {

    private val orderHelper = OrderHelperFE()

    @Test
    @DisplayName("Create an order and check order popup window")
    @Tags(Tag("frontend"), Tag("regress"), Tag("orders"))
    fun ordersPopupInfoCheck() {
        val firstPopularItem = MainPage().open().getPopularProducts().first()
        firstPopularItem.btnIncrement.click()

        MainPage().navigateHeader().clickLink("Cart")
        CartPopup().checkoutButtonClick()

        val orderId = OrderPopup().getOrderId()
        orderHelper.ordersForGC(orderId)

        OrderPopup().orderPopupTitle() shouldBe "Order Created!"
        OrderPopup().orderPopupWindowId() shouldBe "Order ID: ${OrderPopup().getOrderId()}"
        OrderPopup().orderPopupWindowStatus() shouldBe "Status: PENDING"
    }
}