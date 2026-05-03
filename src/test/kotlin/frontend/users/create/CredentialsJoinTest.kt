package frontend.users.create

import frontend.helpers.UserHelper
import frontend.components.popup.CreateAccountPopup
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CredentialsJoinTest: TestBaseUI() {

    private val userHelper = UserHelper()

    @Test
    @DisplayName("Create account validation test via UI")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    fun createAccountTest() {
        val username = "testBasic"
        val email = "testBasic@testBasic.com"
        val password = "testBasic"

        MainPage().navigateHeader().clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)
        userHelper.usersForGC(email)

        MainPage().navigateHeader().headerAvatarPic() shouldBe true
        MainPage().navigateHeader().headerLogoutButton() shouldBe true
    }

    @DisplayName("Negative: create account via UI using invalid credentials")
    @Tags(Tag("frontend"),Tag("regress"),Tag("users"))
    @ParameterizedTest(name = "Username: {0}, Email {1}, Password: {2}, Error: {3}")
    @CsvSource(
        "'', '', '', 'Please enter username, email and password'",
        "'user', '', '', 'Please enter username, email and password'",
        "'user', 'user@user.com', '', 'Please enter username, email and password'",
        "'nonexistentUser1','nonexistantUser1@nonexistantUser1.com','nonexistentUser1', 'Something went wrong. Please verify request.'")
    fun createAccountValidation(username: String, email: String, password: String, expectedError: String) {

        MainPage().navigateHeader().clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)
            .getJoinErrorMessage() shouldBe expectedError
    }
}