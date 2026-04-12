package frontend.pages

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step

class OrdersPage {
    private val inputOrderId get() = element("[placeholder='Order ID']")

    @Step("Check if placeholder 'Order ID'-text is present in input field")
    fun shouldHaveCorrectPlaceholder() {
        inputOrderId.shouldHave(attribute("placeholder", "Order ID"))
    }
}