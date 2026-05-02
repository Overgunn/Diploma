package database

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
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
    @DisplayName("Create and check user with basic JDBC kotlin helper")
    @Tags(Tag("DB"),Tag("regress"),Tag("users"))
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

        val users = jdbcClient.getUsers().firstOrNull { it.email == email}
        userHelper.usersForGC(email)

        users shouldNotBe null
        users?.username shouldBe username
        users?.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users!!.id).getAsObject()

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }
}