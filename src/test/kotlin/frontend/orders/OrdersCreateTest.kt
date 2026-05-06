package frontend.orders

import frontend.components.popup.CartPopup
import frontend.components.popup.OrderPopup
import frontend.helpers.OrderHelperFE
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class OrdersCreateTest : TestBaseUI() {

    private val orderHelper = OrderHelperFE()

    @Test
    @DisplayName("Create an order and check it on orders page")
    @Tags(Tag("frontend"), Tag("regress"), Tag("orders"))
    fun createOrderUnauthorized() {
        MainPage().open().getPopularProducts().first().btnIncrement.click()
        val mainPageItem = MainPage().getPopularProducts().first()

        MainPage().navigateHeader().clickLink("Cart")
        CartPopup().checkoutButtonClick()

        val orderId = OrderPopup().getOrderId()
        OrderPopup().orderPopupCloseBtn()
        orderHelper.ordersForGC(orderId)

        MainPage().navigateHeader().clickLink("Order")
        OrdersPage().enterOrderId(orderId)

        sleep(2000) // RC

        val orderedItems = OrdersPage().getOrderItems()

        orderedItems.size.shouldBe(1)
        mainPageItem.name shouldBe orderedItems.first().name
    }

    @Test
    @DisplayName("Check non-existent order ID")
    @Tags(Tag("frontend"), Tag("regress"), Tag("orders"))
    fun findNonexistentOrder() {
        MainPage().navigateHeader().clickLink("Orders")

        val orderId = 0
        OrdersPage()
            .open()
            .enterOrderId(orderId)
            .getOrderErrorMessage("Order with id:$orderId not found")
    }
}