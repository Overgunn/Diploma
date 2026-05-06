package frontend.components.popup

import com.codeborne.selenide.Selenide.element
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class JoinPopup {
    private val joinDialogPopupTitle get() = element(byDataTestId("create-title"))
    private val joinDialogPopupCreate get() = element(byDataTestId("create-login"))

    @Step("Checkout popup window title is 'Create Account'")
    fun getTitle(): String {
        return joinDialogPopupTitle.text
    }

    @Step("Click 'Log in to your account' link")
    fun signInPopupClick(): JoinPopup {
        joinDialogPopupCreate.click()
        return this
    }
}