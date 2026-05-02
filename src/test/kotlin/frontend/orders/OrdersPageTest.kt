package frontend.orders

import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test



class OrdersPageTest: TestBaseUI() {

    @Test
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    @DisplayName("Header navigation check: go to Orders page")
    fun ordersPlaceholderCheck() {
        MainPage().navigateHeader().clickLink("Orders")

        val ordersPage = OrdersPage()
        ordersPage.placeHolderName("Order ID")
    }

    @Test
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    @DisplayName("Enter non-existent order ID and check error message")
    fun findNonexistentOrder() {
        MainPage().navigateHeader().clickLink("Orders")

        val orderId = 1
        OrdersPage()
            .open()
            .enterOrderId(orderId)
            .getOrderErrorMessage("Order with id:$orderId not found")

    }
}