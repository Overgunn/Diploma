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

class LoginDialogTest : TestBaseUI() {

    @Test
    @DisplayName("Check login window popup")
    @Tags(Tag("frontend"), Tag("regress"), Tag("users"))
    fun loginDialogPopupCheck() {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .getLoginWindowTitle() shouldBe "Login"
    }
}