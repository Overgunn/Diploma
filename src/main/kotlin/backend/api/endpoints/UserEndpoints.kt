package backend.api.endpoints

import backend.api.endpoints.headers.Headers
import backend.api.models.users.createUser.CreateUserRequest
import backend.api.models.users.createUser.CreateUserResponse
import backend.api.models.users.updateUser.UpdateUserRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserEndpoints {

    @GET("users/")
    fun getUsers(
        @Header(Headers.AUTHORIZATION) token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<CreateUserResponse>>

    @POST("users/create")
    fun createUser(@Body body: CreateUserRequest): Call<CreateUserResponse>

    @PUT("users/{id}")
    fun putUserById(
        @Header(Headers.AUTHORIZATION) token: String,
        @Path("id") id: Int,
        @Body body: UpdateUserRequest
    ): Call<CreateUserResponse>

    @GET("users/{id}")
    fun getUserById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int): Call<CreateUserResponse>

    @DELETE("users/{id}")
    fun deleteUserById(@Header(Headers.AUTHORIZATION) token: String, @Path("id") id: Int): Call<ResponseBody>
}