package en.QAguru.frontend.helpers.misc

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byName
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.openqa.selenium.By

class GoogleSearchPage {
    private val googleSearchInput get() = element(By.name("q"))
    private val searchButtonClick get() = element(byName("btnK"))
    private val firstSearchResult get() = element(byXpath("//cite[contains(text(), 'https://selenide.org/')]"))

    @Step("Search google for selenide")
    fun search(search: String): GoogleSearchPage {
        googleSearchInput.value = search
        searchButtonClick.click()
        return this
    }

    @Step("Get first search result text")
    fun searchResultCheck(): String {
        return firstSearchResult.shouldBe(visible).text
    }
}