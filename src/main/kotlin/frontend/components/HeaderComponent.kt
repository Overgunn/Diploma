package frontend.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.popup.CartPopup
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import io.qameta.allure.Step

class HeaderComponent {
    private val linksHeader get() = elements(byDataTestGroup("nav-link"))
    private val headerUserPic: SelenideElement get() = element("img.avatar[alt='Avatar']")

    @Step("Clicks header {name} link")
    fun clickLink(name: String): HeaderComponent {
        linksHeader.first { it.text.contains(name)}.click()
        return this
    }

    @Step("Get all header link names")
    fun getLinkNames(): List<String> {
        return linksHeader.map { it.text }
    }

    @Step("Checks if avatar is present on the header after successful logging in")
    fun isAvatarVisible(): Boolean {
        headerUserPic.shouldBe(Condition.appear)
        return headerUserPic.isDisplayed
    }

    @Step("Get cart popup")
    fun navigateCartPopup(): CartPopup {
        return CartPopup()
    }
}