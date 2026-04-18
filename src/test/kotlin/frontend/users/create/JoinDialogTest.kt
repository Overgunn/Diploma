package frontend.join

import frontend.helpers.BasicUiHelper
import frontend.components.popup.JoinPopup
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class JoinDialogTest: BasicUiHelper() {

    @Test
    @Tags(Tag("create-user"),Tag("frontend"))
    @DisplayName("Check popup window after clicking 'Join' header button")
    fun joinDialogPopup() {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .getTitle() shouldBe "Create Account"
    }
}