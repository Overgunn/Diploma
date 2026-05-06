package backend.api.endpoints

import backend.api.endpoints.headers.Headers
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.orders.UpdateOrderRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface OrdersEndpoints {

    @GET("orders/")
    fun getAllOrders(
        @Header(Headers.AUTHORIZATION) token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<CreateOrderResponse>>

    @GET("orders/{id}")
    fun getOrderById(@Path("id") id: Any): Call<CreateOrderResponse>

    @GET("orders/user/{id}")
    fun getOrderByUserId(@Path("id") id: Any): Call<List<CreateOrderResponse>>

    @POST("orders/create")
    fun createOrder(@Body body: CreateOrderRequest): Call<CreateOrderResponse>

    @PUT("orders/{id}/status")
    fun updateOrderStatus(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int?,
        @Body body: UpdateOrderRequest
    ): Call<CreateOrderResponse>

    @DELETE("orders/{id}")
    fun deleteOrderById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Any): Call<ResponseBody>
}