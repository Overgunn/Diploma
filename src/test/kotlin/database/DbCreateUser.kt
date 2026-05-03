package database

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import database.helpers.ExposedHelper
import database.helpers.JDBCHelper
import frontend.helpers.UserHelper
import frontend.components.popup.CreateAccountPopup
import frontend.helpers.TestBaseUI
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class DbCreateUser: TestBaseUI() {

    private val jdbcClient = JDBCHelper()
    private val userHelper = UserHelper()
    private val controllers = Controllers()

    @Test
    @Tags(Tag("DB"),Tag("regress"),Tag("users"))
    @DisplayName("Create and check user with basic JDBC kotlin helper")
    fun testCreateUserWithJdbcHelper() {
        val username = "testDBuser"
        val email = "testDBuser@testDBuser.com"
        val password = "testDBuser"

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)

        sleep(2000) //запись в бд идет дольше создания пользователя \ race-condition

        val users = jdbcClient.getUsers().first { it.email == email}
        userHelper.usersForGC(email)

        users shouldNotBe null
        users.username shouldBe username
        users.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users.id).getAsObject()

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }

    @Test
    @Tags(Tag("DB"),Tag("regress"),Tag("users"))
    @DisplayName("Create and check user with Exposed DB helper")
    fun testCreateUserWithExposedHelper() {
        val exposedHelper = ExposedHelper()
        val username = "testExposed"
        val email = "testExposed@testExposed.com"
        val password = "testExposed"

        MainPage()
            .navigateHeader()
            .clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)
        userHelper.usersForGC(email)

        val users = exposedHelper.getAllUsersExposed().first { it.email == email}

        users shouldNotBe null
        users.username shouldBe username
        users.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users.id).getAsObject()

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }
}