package backend.api.models.orders

import backend.api.models.ErrorResponse

object OrderErrorResponse {

    val invalidOrderProduct = ErrorResponse(
    code = 400,
    reason = "Something went wrong. Please verify request.")

    val wrongOrderStatus = ErrorResponse(
        code = 400,
        reason = "Something went wrong. Please verify request.")

}