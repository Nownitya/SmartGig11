package com.smartgig.network.repository

import com.smartgig.network.Api.UserApi
import com.smartgig.network.UserDetailRequest
import com.smartgig.network.UserDetailResponse
import retrofit2.Response

class EmpDataRepository {
    suspend fun userDetail(userDetailRequest: UserDetailRequest): Response<UserDetailResponse>? {
        return UserApi.getApi()?.empDetails(id = userDetailRequest.empId)
    }
}