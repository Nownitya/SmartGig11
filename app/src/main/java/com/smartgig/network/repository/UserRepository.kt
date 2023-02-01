package com.smartgig.network.repository

import android.widget.Toast
import com.smartgig.network.*
import com.smartgig.network.Api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>?{
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }


    suspend fun userDetail(userDetailRequest: UserDetailRequest): Response<UserDetailResponse>? {
        return UserApi.getApi()?.empDetails(id = userDetailRequest.empId)
    }


}