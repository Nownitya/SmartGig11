package com.smartgig.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smartgig.constant.Constant
import com.smartgig.network.Api.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val authInterceptor: AuthInterceptor = AuthInterceptor()
    var mHttpLoginInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    var mOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(mHttpLoginInterceptor)
//        .addInterceptor(authInterceptor)
        .build()
//    val gson = GsonBuilder()
//        .setLenient()
//        .create()
    var mRetrofit: Retrofit? = null
    val client:Retrofit?
    get() {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(Constant.Base_Url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return mRetrofit
    }
}