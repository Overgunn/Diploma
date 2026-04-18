package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeleteProductTest: Controllers() {

    val authHelper = AuthorizationHelper()

    @Test
    @DisplayName("Create and delete product")
    fun deleteProduct() {
        val userToken = authHelper.getNewToken()

        val product = products.createProduct(token = userToken, CreateProductRequest(
            name = "Delete Test Coffee",
            price = 1.2,
            description = "Some description")).getAsObject()

        val delete = products.deleteProductById(token = userToken, product.id)
        GarbageCollector.products.remove(product.id)

        delete.code() shouldBe 200
    }
}