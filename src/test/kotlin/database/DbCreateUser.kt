package database

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import backend.helpers.GarbageCollector
import frontend.components.popup.CreateAccountPopup
import frontend.helpers.BasicUiHelper
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DbCreateUser: BasicUiHelper() {

    private val controllers = Controllers()

    @Tags(Tag("create-user"),Tag("DB"))
    @DisplayName("Create and check user with basic JDBC kotlin helper")
    @ParameterizedTest(name = "Username: {0}, Email: {1}, Password: {2}")
    @CsvSource("'testBasic','testBasic','testBasic'")
    fun testCreateUserWithJdbcHelper(username: String, email: String, password: String) {
        MainPage()
            .navigateHeader()
            .clickLink("Join")

        CreateAccountPopup()
            .joinAs(username, email, password)

        val jdbcClient = JDBCHelper()

        val users = jdbcClient.getUsers().firstOrNull { it.email == email}
        println(users)

        users?.let {
            GarbageCollector.user.add(it.id)
        }

        users shouldNotBe null
        users?.username shouldBe username
        users?.email shouldBe email

        val apiUser = controllers.users.getUserById(id = users!!.id).getAsObject()
        println(apiUser)

        users shouldNotBe null
        users.username shouldBe apiUser.username
        users.email shouldBe apiUser.email
    }
}