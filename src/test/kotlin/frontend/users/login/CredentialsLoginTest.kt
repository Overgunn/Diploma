package frontend.users.login

import frontend.components.popup.JoinPopup
import frontend.components.popup.LoginPopup
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CredentialsLoginTest: TestBaseUI() {

    @Test
    @DisplayName("Login into account via UI")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    fun loginCheck() {
        val email = "positiveLoginTest@positiveLoginTest.com"
        val password = "positiveLoginTest"

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)

        MainPage().navigateHeader().headerAvatarPic() shouldBe true
        MainPage().navigateHeader().headerLogoutButton() shouldBe true
    }

    @DisplayName("Negative: login into account via UI")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    @ParameterizedTest(name = "Email {0}, Password: {1}, Error: {2}")
    @CsvSource(
        "'', '', 'Please enter email and password'",
        "'1@1.com', '', 'Please enter email and password'",
        "'wrongpass@wrongpass.com', 'wrongpass1', 'Wrong password: wrongpass | wrongpass1'"
    )
    fun negativeLoginCheck(email: String, password: String, error: String) {

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .loginWindowInput(email, password)
            .getErrorText() shouldBe error
    }
}