package frontend.components.popup

import com.codeborne.selenide.Selectors.shadowCss
import com.codeborne.selenide.Selenide.element
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class CreateAccountPopup {

    private val usernameInput get() = element(byDataTestId("create-username")).find(shadowCss(".input"))
    private val emailInput get() = element(byDataTestId("create-email")).find(shadowCss(".input"))
    private val passwordInput get() = element(byDataTestId("create-password")).find(shadowCss(".input"))
    private val submitButton get() = element(byDataTestId("create-submit"))
    private val errorMessage get() = element(byDataTestId("create-error"))

    @Step("User logs in with given credentials")
    fun joinAs(username: String, email: String, password: String): CreateAccountPopup {
        usernameInput.value = username
        emailInput.value = email
        passwordInput.value = password
        submitButton.click()
        return this
    }
    @Step("Errors for invalid credentials")
    fun getJoinErrorMessage(): String {
        return errorMessage.text
    }
}