package backend.api.models.users.updateUser

data class UpdateUserRequest(
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null
)