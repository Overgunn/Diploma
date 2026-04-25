package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class CreateProductTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @Tags(Tag("create-product"),Tag("backend"))
    @DisplayName("Create and get product")
    fun createProduct() {
        val userToken = authHelper.getNewToken()

        val product = products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Create Test Coffee",
                price = 1.2,
                description = "Some description")).getAsObject()

        val foundProduct = products.getProducts().getAsObject()
            .firstOrNull { it.id == product.id }

        foundProduct?.shouldBeEqual(product)
    }
}