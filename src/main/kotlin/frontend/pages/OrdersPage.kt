package frontend.pages

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.list.OrderItem
import frontend.components.list.OrderItems
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

class OrdersPage {
    private val inputOrderId get() = element("[placeholder='Order ID']")

    private val orderIdInput get() = element("[placeholder='Order ID']").find(shadowCss("input"))

    private val orderItems: ElementsCollection get() = elements(byDataTestGroup("order-card"))

    @Step("Check if placeholder 'Order ID'-text is present in input field")
    fun shouldHaveCorrectPlaceholder() {
        inputOrderId.shouldHave(attribute("placeholder", "Order ID"))
    }

    @Step("Enter order ID: {id}")
    fun enterOrderId(id: Int): OrdersPage {
        orderIdInput.value = id.toString()
        return this
    }

    @Step("Get products object list")
    fun getOrderItems(): List<OrderItem> {
        return OrderItems(orderItems).getItems()
    }
}