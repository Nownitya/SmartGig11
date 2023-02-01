package com.smartgig.network.repository

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("adminId")
    val adminId: Int,
//    @SerializedName("msg")
//    val msg: String,

//    @SerializedName("id")
//    val id: Int,
    @SerializedName("adminName")
    val adminName: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("isPasswordChanged")
    val isPasswordChanged: Boolean = false,
    @SerializedName("role")
    val role: String,
    @SerializedName("token")
    val token: String
//    @SerializedName("data")
//    val `data`:Data


)
