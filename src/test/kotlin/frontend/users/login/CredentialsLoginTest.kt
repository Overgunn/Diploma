package frontend.users.login

import frontend.components.popup.JoinPopup
import frontend.components.popup.LoginPopup
import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CredentialsLoginTest: BasicUiHelper() {

    @DisplayName("Parametrized login validation positive test")
    @Tags(Tag("login-user"),Tag("frontend"))
    @ParameterizedTest(name = "Email {0}, Password: {1}")
    @CsvSource(
        "'positiveLoginTest@positiveLoginTest.com', 'positiveLoginTest'"
    )
    fun loginValidation(email: String, password: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)

        val isVisible = MainPage().navigateHeader().checkUserPic()
        isVisible shouldBe true
    }

    @DisplayName("Parametrized login validation negative test")
    @Tags(Tag("login-user"),Tag("frontend"))
    @ParameterizedTest(name = "Email {0}, Password: {1}, Error: {2}")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'1@1.com', '', 'Please enter email and password'",
        "'1@1.com', '111', 'Invalid email or password'"
    )
    fun loginValidation(email: String, password: String, expectedError: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)
            .getErrorText(expectedError)
    }
}