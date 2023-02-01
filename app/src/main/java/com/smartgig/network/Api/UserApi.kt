package com.smartgig.network.Api

import com.smartgig.network.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @POST("smg/admin/adminLogin")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("smg/admin/getAdminDetails/{id}")
    suspend fun empDetails(@Path("id") id:String): Response<UserDetailResponse>

//    @POST("smg/admin/getAdminDetails/{id}")
//    suspend fun empDetails(@Path("id") id: String,@Body userDetailRequest: UserDetailRequest): Response<UserDetailResponse>
//    suspend fun empDetails(@Path("empId") userDetailRequest: UserDetailRequest): Response<UserDetailResponse>


    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)

        }
    }


//    @GET()


}