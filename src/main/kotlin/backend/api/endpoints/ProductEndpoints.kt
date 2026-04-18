package backend.api.endpoints

import backend.api.endpoints.Headers.Headers
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.api.models.products.UpdateProductRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductsEndpoints {
    @GET("products/")
    fun getProducts(): Call<List<CreateProductResponse>>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Any): Call<CreateProductResponse>

    @POST("products/create")
    fun postCreateProduct(@Header(Headers.AUTHORIZATION) token: String, @Body body: CreateProductRequest): Call<CreateProductResponse>

    @PUT("products/{id}")
    fun updateProductById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int, @Body body: UpdateProductRequest): Call<CreateProductResponse>

    @DELETE("products/{id}")
    fun deleteProductById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Any): Call<ResponseBody>
}