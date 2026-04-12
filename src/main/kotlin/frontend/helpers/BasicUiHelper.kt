package frontend.helpers

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BasicUiHelper {

    init {
        Configuration.baseUrl = "http://localhost:4000"
        Configuration.pageLoadStrategy = "normal"
        Configuration.reopenBrowserOnFail = true
        Configuration.timeout = 5000
        //Configuration.browser = DriverProvider::class.java.name
    }

    @BeforeEach
    fun openBrowser() {
        Selenide.open("/")
    }

    @AfterEach
    fun clearBrowser() {
        Selenide.clearBrowserCookies()
        Selenide.clearBrowserLocalStorage()
    }
}