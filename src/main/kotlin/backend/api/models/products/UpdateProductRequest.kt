package backend.api.models.products

data class UpdateProductRequest(
    var name: String?,
    var price: Double,
    var description: String
)