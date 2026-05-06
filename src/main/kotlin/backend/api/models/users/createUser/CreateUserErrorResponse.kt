package backend.api.models.users.createUser

import backend.api.models.ErrorResponse

val emptyCredentials = ErrorResponse(
    code = 400,
    reason = "User details cannot be null or blank"
)

val credentialsErrors = ErrorResponse(
    code = 400,
    reason = "Something went wrong. Please verify request."
)