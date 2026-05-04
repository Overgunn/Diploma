package frontend.orders

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import database.helpers.JDBCHelper
import frontend.components.popup.CartPopup
import frontend.components.popup.CreateAccountPopup
import frontend.components.popup.OrderPopup
import frontend.helpers.OrderHelperFE
import frontend.helpers.TestBaseUI
import frontend.helpers.UserHelper
import frontend.pages.MainPage
import frontend.pages.OrdersPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class OrdersCreateTest: TestBaseUI() {

    private val controllers = Controllers()
    private val userHelper = UserHelper()
    private val orderHelper = OrderHelperFE()

    @Test
    @DisplayName("Create an order and check it on orders page")
    @Tags( Tag("frontend"),Tag("regress"),Tag("orders"))
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

        sleep(2000)

        val orderedItems = OrdersPage().getOrderItems()

        orderedItems.size.shouldBe(1)
        mainPageItem.name shouldBe orderedItems.first().name
    }

    @Test
    @DisplayName("Join as user and create an order, check it on orders page")
    @Tags( Tag("frontend"),Tag("regress"),Tag("orders"))
    fun createOrderAsAuthorized() {
        val username = "orderAutotest"
        val email = "orderAutotest@orderAutotest.com"
        val password = "orderAutotest"
        val jdbcClient = JDBCHelper()

        MainPage().navigateHeader().clickLink("Join")
        CreateAccountPopup()
            .joinAs(username, email, password)
        userHelper.usersForGC(email)

        val dbUser = jdbcClient.getNewUser().first { it.email == email }

        MainPage().open().getPopularProducts().first().btnIncrement.click()
        MainPage().navigateHeader().clickLink("Cart")
        CartPopup().checkoutButtonClick()

        val orderId = OrderPopup().getOrderId()
        orderHelper.ordersForGC(orderId)
        OrderPopup().orderPopupCloseBtn()

        val usersOrder = controllers.orders.getOrdersByUserId(dbUser.id)
            .getAsObject()
            .first { it.id == orderId }

        usersOrder.id shouldBe orderId

        val dbOrder = jdbcClient.getNewOrder().first { it.id == orderId }

        dbOrder.products shouldNotBe null
        dbOrder.userId shouldBe dbUser.id
    }

    @Test
    @DisplayName("Check non-existent order ID")
    @Tags(Tag("frontend"),Tag("regress"),Tag("orders"))
    fun findNonexistentOrder() {
        MainPage().navigateHeader().clickLink("Orders")

        val orderId = 0
        OrdersPage()
            .open()
            .enterOrderId(orderId)
            .getOrderErrorMessage("Order with id:$orderId not found")
    }
}