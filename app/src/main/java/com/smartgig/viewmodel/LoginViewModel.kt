package com.smartgig.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartgig.utils.NetworkResult
import com.smartgig.network.LoginRequest
import com.smartgig.network.LoginResponse
import com.smartgig.network.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepo = UserRepository()
    val loginResult: MutableLiveData<NetworkResult<LoginResponse>> = MutableLiveData()

//    val userDetailResult: MutableLiveData<NetworkResult<UserDetailRequest>> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        loginResult.value = NetworkResult.Loading()
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(email = email, password = password)
                val response = userRepo.loginUser(loginRequest = loginRequest)
                if (response?.code() == 200) {
                    loginResult.value = NetworkResult.Success(response.body())
                } else {
                    loginResult.value = NetworkResult.Error(response?.message())
                }
            } catch (e: Exception) {
                loginResult.value = NetworkResult.Error(e.message)
            }
        }
    }

//    fun empDetails(empId: String) {
//        userDetailResult.value = NetworkResult.Loading()
//        viewModelScope.launch {
//            try {
//                val empRequest = UserDetailRequest(empId = empId)
//                val responseEmp = userRepo.userDetail(userDetailRequest = empRequest)
//                if (responseEmp?.code() == 200) {
//                    userDetailResult.value = NetworkResult.Success()
//                } else {
//                    userDetailResult.value = NetworkResult.Error(responseEmp?.message())
//                }
//            } catch (e: java.lang.Exception) {
//                userDetailResult.value = NetworkResult.Error(e.message)
//            }
//        }
//
//    }
//    fun userDetails(adminId:Int, designation:String, email: String,image: String, name: String){
//        loginResult.value = NetworkResult.Loading()
//        viewModelScope.launch {
//            try {
//                val dataRequest = UserDetailRequest(
//
//                    adminId =  adminId,
//                    designation = designation,
//                    email = email,
//                    image= image,
//                    name = name,
//                )
//            }
//        }
//
//    }

}