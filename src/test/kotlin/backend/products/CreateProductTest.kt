package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.ProductErrorResponse.invalidInput
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import frontend.helpers.TestBaseUI
import frontend.pages.ProductsPage
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class CreateProductTest: TestBaseUI() {

    private val controllers = Controllers()
    private val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create and get product")
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    fun createProductCheck() {
        val userToken = authHelper.getNewToken()

        val product = controllers.products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Create coffee name with 60 symbols in the description field",
                price = 1.0,
                description = "And here goes description with the exact one hundred symbols fitted in one field perfectly for test")).getAsObject()

        val foundProduct = controllers.products.getProducts().getAsObject()
            .first { it.id == product.id }

        foundProduct.shouldBeEqual(product)
    }

    @Test
    @DisplayName("Create product with invalid data and check error")
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    fun createProductWithInvalidData() {
        val userToken = authHelper.getNewToken()

        val product = controllers.products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Create coffee name with 60 symbols in the description field + 4",
                price = 2.1,
                description = "And here goes description with the exact one hundred symbols fitted in one field perfectly for test"))

        val error = product.getErrorAsObject<ErrorResponse>()

        error shouldBe invalidInput
    }

    @Test
    @DisplayName("Create product and find it on products page")
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    fun createItemForProductPage() {
        val userToken = authHelper.getNewToken()

        val newProduct = controllers.products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Test product name",
                price = 3.2,
                description = "Test product description")).getAsObject()

        val products = ProductsPage().open().getProductItems()
        val foundProduct = products.first {it.name == newProduct.name}

        foundProduct.name shouldBe newProduct.name
        foundProduct.price shouldBe newProduct.price
    }
}