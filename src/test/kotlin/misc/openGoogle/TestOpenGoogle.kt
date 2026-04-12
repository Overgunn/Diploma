package misc.openGoogle

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import en.QAguru.frontend.helpers.misc.GoogleSearchPage
import frontend.helpers.BasicUiHelper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import kotlin.concurrent.thread


class TestOpenGoogle : BasicUiHelper() {

    init {
        Configuration.baseUrl = "http://google.com"
        Configuration.pageLoadStrategy = "normal"
        Configuration.reopenBrowserOnFail = true
        Configuration.timeout = 5000
    }

    @Test
    @DisplayName("Open google main page")
    fun openGoogleMainPage() {
        Selenide.open("/")
        Selenide.title() shouldBe "Google"

        val searchResult = GoogleSearchPage()
            .search("Selenide")
            .searchResultCheck()

        searchResult shouldBe "https://selenide.org/"

    }
}