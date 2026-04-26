package frontend.users.login

import frontend.helpers.BasicUiHelper
import frontend.components.popup.JoinPopup
import frontend.components.popup.LoginPopup
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class LoginDialogTest: BasicUiHelper() {

    @Test
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    @DisplayName("Check if login window will open after clicking 'Your account' link")
    fun loginDialogPopup() {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .signInPopupClick()

        LoginPopup()
            .getLoginWindowTitle() shouldBe "Login"
    }
}