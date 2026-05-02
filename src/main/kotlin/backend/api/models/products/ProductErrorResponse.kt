package backend.api.models.products

import backend.api.models.ErrorResponse

object ProductErrorResponse {

    val invalidInput = ErrorResponse(
        code = 400,
        reason = "Something went wrong. Please verify request.")

    val invalidProduct = ErrorResponse(
        code = 400,
        reason = "Something went wrong. Please verify request.")

}