package frontend.users.login

import frontend.components.popup.JoinPopup
import frontend.components.popup.LoginPopup
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CredentialsLoginTest: TestBaseUI() {

    @Test
    @DisplayName("Parametrized login validation positive test")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    fun loginValidation() {
        val email = "positiveLoginTest@positiveLoginTest.com"
        val password = "positiveLoginTest"

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)

        MainPage().navigateHeader().isAvatarVisible() //поправить проверку, что аватар shouldBe true
    }

    @DisplayName("Parametrized login validation negative test")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    @ParameterizedTest(name = "Email {0}, Password: {1}, Error: {2}")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'1@1.com', '', 'Please enter email and password'",
        "'1@1.com', '111', 'Invalid email or password'"
    )
    fun loginValidation(email: String, password: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)
            .getErrorText() //добавить проверку на полученный результат, вынести ошибки в UI error ENUM
    }
}