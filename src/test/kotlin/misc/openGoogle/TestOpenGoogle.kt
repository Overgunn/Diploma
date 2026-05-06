package misc.openGoogle

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import frontend.helpers.TestBaseUI
import frontend.helpers.misc.GoogleSearchPage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TestOpenGoogle : TestBaseUI() {

    init {
        Configuration.baseUrl = "http://google.com"
        Configuration.pageLoadStrategy = "normal"
        Configuration.reopenBrowserOnFail = true
        Configuration.timeout = 5000
    }

    @Test
    @Disabled("DISABLED")
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