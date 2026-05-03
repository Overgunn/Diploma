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
    @DisplayName("Header navigation check: Orders page check")
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    fun ordersPageCheck() {
        MainPage().navigateHeader().clickLink("Orders")

        val ordersPage = OrdersPage()
        ordersPage.placeHolderName("Order ID")
    }
}