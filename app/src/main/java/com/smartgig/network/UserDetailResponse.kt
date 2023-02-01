package com.smartgig.network

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("empId")
    val empId: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
