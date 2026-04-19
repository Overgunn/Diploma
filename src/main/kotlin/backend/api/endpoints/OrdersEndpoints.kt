package backend.api.endpoints

import backend.api.endpoints.Headers.Headers
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.CreateOrderResponse
import backend.api.models.products.UpdateProductRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrdersEndpoints {

    @GET("orders/")
    fun getOrder(@Header(Headers.AUTHORIZATION) token: String, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<List<CreateOrderResponse>>

    @GET("orders/{id}")
    fun getOrderById(@Path("id") id: Any): Call<CreateOrderResponse>

    @GET("orders/users/{id}")
    fun getOrderByUserId(@Path("id") id: Any): Call<List<CreateOrderResponse>>

    @POST("orders/create")
    fun createOrder(@Header(Headers.AUTHORIZATION) token: String, @Body body: CreateOrderRequest): Call<CreateOrderResponse>

    @PUT("orders/{id}/status")
    fun updateOrderStatus(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int, @Body body: UpdateProductRequest): Call<CreateOrderResponse>

}