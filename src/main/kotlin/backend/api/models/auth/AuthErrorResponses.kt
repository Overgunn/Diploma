package backend.api.models.auth

import backend.api.models.ErrorResponse

object AuthErrorResponses {

    val invalidCredentials = ErrorResponse(
        code = 400,
        reason = "Invalid email or password"
    )
}