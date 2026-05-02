package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.ProductErrorResponse.invalidInput
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class CreateProductTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    @DisplayName("Create and get product")
    fun createProductCheck() {
        val userToken = authHelper.getNewToken()

        val product = products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Create coffee name with 60 symbols in the description field",
                price = 1.2,
                description = "And here goes description with the exact one hundred symbols fitted in one field perfectly for test")).getAsObject()

        val foundProduct = products.getProducts().getAsObject()
            .firstOrNull { it.id == product.id }

        foundProduct?.shouldBeEqual(product)
    }

    @Test
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    @DisplayName("Create product with invalid data and check error")
    fun createProductWithInvalidData() {
        val userToken = authHelper.getNewToken()

        val product = products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Create coffee name with 60 symbols in the description field + 4",
                price = 1.2,
                description = "And here goes description with the exact one hundred symbols fitted in one field perfectly for test"))

        val error = product.getErrorAsObject<ErrorResponse>()

        error shouldBe invalidInput
    }
}
//негативные кейсы