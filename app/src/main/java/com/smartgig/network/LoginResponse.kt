package com.smartgig.network

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("adminId")
    val empId: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("adminName")
    val adminName: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("isPasswordChanged")
    val isPasswordChanged: Boolean = false,
    @SerializedName("token")
    val token: String
)


//{
//    data class Data(
//
//        @SerializedName("id")
//        val id: Int,
//        @SerializedName("adminName")
//        val adminName: String,
//        @SerializedName("image")
//        val image: String,
//        @SerializedName("isPasswordChanged")
//        val isPasswordChanged: Boolean = false,
//        @SerializedName("role")
//        val role: String,
//        @SerializedName("token")
//        val token: String
//    )
//}
