package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import frontend.components.HeaderComponent
import frontend.components.list.ProductCard
import frontend.components.list.ProductItem
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class MainPage {
    private val txtTitle get() = element(byDataTestId("main-image-text"))
    private val listPopularProducts: ElementsCollection get() = elements(byDataTestGroup("product-card"))

    @Step("Open main page")
    fun open(): MainPage {
        Selenide.open("/")
        return this
    }

    @Step("Get main page name {string}")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Go to header component")
    fun navigateHeader(): HeaderComponent {
        return HeaderComponent()
    }

    @Step("Get popular products list")
    fun getPopularProducts(): List<ProductItem> {
        return ProductCard(listPopularProducts).getItems()
    }
}