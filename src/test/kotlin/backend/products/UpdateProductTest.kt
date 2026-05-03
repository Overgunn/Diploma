package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.ProductErrorResponse.invalidProduct
import backend.api.models.products.UpdateProductRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class UpdateProductTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create and update product")
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    fun updateProduct() {
        val userToken = authHelper.getNewToken()

        val product = products.createProduct(token = userToken,
            CreateProductRequest(
                name = "Update Test Coffee",
                price = 1.2,
                description = "Some description")).getAsObject()

        val updateProduct = UpdateProductRequest(
            name = "updated product name",
            price = 1.33,
            description = "updated description"
        )

        val updatedProduct = products.updateProduct(token = userToken, id = product.id, body = updateProduct)?.getAsObject()

        updatedProduct?.name shouldBe updateProduct.name
        updatedProduct?.price shouldBe updateProduct.price
        updatedProduct?.description shouldBe updateProduct.description
    }

    @Test
    @DisplayName("Update non-existent product")
    @Tags(Tag("regress"),Tag("backend"),Tag("products"))
    fun updateNonexistentProduct() {
        val userToken = authHelper.getNewToken()

        val updateProduct = UpdateProductRequest(
            name = "updated product name",
            price = 1.33,
            description = "updated description"
        )

        val updatedProduct = products.updateProduct(token = userToken, id = 0, body = updateProduct)
        val error = updatedProduct?.getErrorAsObject<ErrorResponse>()

        error shouldBe invalidProduct
    }
}