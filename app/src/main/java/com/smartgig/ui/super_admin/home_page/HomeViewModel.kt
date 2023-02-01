package com.smartgig.ui.super_admin.home_page

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smartgig.utils.NetworkResult
import com.smartgig.network.UserDetailRequest
import com.smartgig.network.UserDetailResponse
import com.smartgig.network.repository.EmpDataRepository
import com.smartgig.network.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application):AndroidViewModel(application){
    private val userRepo = UserRepository()
    private val empRepo = EmpDataRepository()
    val userDetailResult: MutableLiveData<NetworkResult<UserDetailResponse>> = MutableLiveData()


    fun empDetails(empId: String) {
        userDetailResult.value = NetworkResult.Loading()
        viewModelScope.launch {
//            userRepo.userDetail( )
            try {
                val empRequest = UserDetailRequest(empId = empId)
                val responseEmp = empRepo.userDetail(userDetailRequest = empRequest)
                if (responseEmp?.code() == 200) {
                    userDetailResult.value = NetworkResult.Success(responseEmp.body())
                } else {
                    userDetailResult.value = NetworkResult.Error(responseEmp?.message())
                }
            } catch (e: java.lang.Exception) {
                userDetailResult.value = NetworkResult.Error(e.message)
            }
        }

    }


    
}