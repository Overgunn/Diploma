package frontend.users.create

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import backend.helpers.GarbageCollector
import frontend.components.popup.CreateAccountPopup
import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ValidCredentialsJoin: BasicUiHelper() {

    private val controllers = Controllers()

    @DisplayName("Create account test")
    @Tags(Tag("create-user"),Tag("frontend"))
    @ParameterizedTest(name = "username: {0}, email {1}, password: {2}")
    @CsvSource("'autotest','autotest@autotest.com','autotest'")
    fun createAccountTest(username: String, email: String, password: String) {

        MainPage().navigateHeader().clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)

        MainPage().navigateHeader().checkUserPic()

        controllers.users.getAllUsers()
            .getAsObject()
            .firstOrNull { it.email == email }
            ?.let { GarbageCollector.user.add(it.id)
                println("Added to GC: ${it.id}, email: ${it.email}")
            }
    }
}