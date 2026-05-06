package backend.api.models.products

data class UpdateProductRequest(
    var name: String? = null,
    var price: Double? = null,
    var description: String? = null
)