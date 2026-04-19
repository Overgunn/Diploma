package frontend.orders

import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test



class OrdersPageTest: BasicUiHelper() {

    @Test
    @Tags(Tag("orders-page"), Tag("frontend"))
    @DisplayName("Header navigation check: go to Orders page")
    fun ordersCheck() {
        MainPage().navigateHeader().clickLink("Orders")

        val ordersPage = OrdersPage()
        ordersPage.shouldHaveCorrectPlaceholder()
    }
}