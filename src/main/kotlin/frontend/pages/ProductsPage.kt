package frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.components.list.ProductItem
import frontend.components.list.ProductCard
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

class ProductsPage {
    private val productPageTitle: SelenideElement get() = element(byDataTestId("products-title"))
    private val listProductItems: ElementsCollection get() = elements(byDataTestGroup("product-card"))

    @Step("Open products page")
    fun open(): ProductsPage {
        Selenide.open("/products")
        return this
    }

    @Step("Check product page title")
    fun shouldHaveTitle(): String {
        return productPageTitle.text
    }

    @Step("Get products object list")
    fun getProductItems(): List<ProductItem> {
        return ProductCard(listProductItems).getItems()
    }
}