package frontend.join

import frontend.helpers.TestBaseUI
import frontend.components.popup.JoinPopup
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class JoinDialogTest: TestBaseUI() {

    @Test
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    @DisplayName("Check popup window after clicking 'Join' header button")
    fun joinDialogPopup() {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        JoinPopup()
            .getTitle() shouldBe "Create Account"
    }
}