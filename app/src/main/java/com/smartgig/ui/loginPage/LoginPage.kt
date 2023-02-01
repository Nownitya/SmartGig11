package com.smartgig.ui.loginPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.smartgig.MainActivity
import com.smartgig.databinding.ActivityLoginPageBinding
import com.smartgig.utils.NetworkResult
import com.smartgig.network.LoginResponse
import com.smartgig.utils.SessionManager
import com.smartgig.utils.TokenManager
import com.smartgig.viewmodel.LoginViewModel

class LoginPage : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private val viewModel by viewModels<LoginViewModel>()
    lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /**
         * If token available then re open the profile without Login process
         * TODO: According to profile open the screen ("Super_Admin, Admin, Employee)
         *

         */

        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            Toast.makeText(this,"$token", Toast.LENGTH_SHORT).show()
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is NetworkResult.Loading -> {
                    showLoading()
                }
                is NetworkResult.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }
                is NetworkResult.Error -> {
                    processError(it.msg)

                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.loginBtn.setOnClickListener {
            doLogin()

        }

    }




    private fun doLogin() {
        val email = binding.emailET.text.toString()
        val password = binding.passwordET.text.toString()

        viewModel.loginUser(email = email, password = password)

    }
    private fun showLoading() {
        binding.pgBar.visibility = View.VISIBLE

    }
    private fun stopLoading() {
        binding.pgBar.visibility = View.GONE
    }
    private fun processLogin(data: LoginResponse?) {
        showToast("Msg: " +data?.msg)
        if (!(data?.token).isNullOrEmpty()) {
            data?.token?.let {

                SessionManager.saveAuthToken(this, it)
                //  Todo: saving token not working.
//                tokenManager.saveAuthTokens(data.token)
//                navigateToHome()

                navigateToHome()
                tokenManager.saveAuthTokens(data.token)
                Toast.makeText(this, "Super_Admin", Toast.LENGTH_SHORT).show()

//                if ((data.role) == "SUPER_ADMIN") {
//                    navigateToHome()
//                    tokenManager.saveAuthTokens(data.token)
//                    Toast.makeText(this, "Super_Admin", Toast.LENGTH_SHORT).show()
//                }
//                else if ((data.role) == "ADMIN") {
//                    navigateToAdminHome()
//                    Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show()
//                } else if (data.role == "Employee") {
//                    navigateToEmployeeHome()
//                    Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "Please connect to Authority", Toast.LENGTH_SHORT)
//
//                }

            }
        }
        if (!(data?.empId).isNullOrEmpty()) {
            data?.empId.let {
                if (it != null) {
                    SessionManager.saveAuthID(this, it)
                }
            }
        }
        if (!(data?.adminName).isNullOrEmpty()) {
            data?.adminName.let {
                Toast.makeText(this, "admin_name ${data?.adminName}",Toast.LENGTH_SHORT).show()
                if (it != null) {
                    SessionManager.saveAuthName(this,it)
                }
            }
        }
        if (!(data?.empId).isNullOrEmpty()) {
            data?.empId.let {
                Toast.makeText(this, "admin_Id ${data?.adminName}",Toast.LENGTH_LONG).show()
                if (it != null) {
                    SessionManager.saveAuthID(this,it)
                }
            }
        }
        if (!(data?.role).isNullOrEmpty()) {
            data?.role.let {
                Toast.makeText(this, "admin_Role ${data?.role}",Toast.LENGTH_LONG).show()
                if (it != null) {
                    SessionManager.saveAuthRole(this,it)
                }
            }
        }
        if (!(data?.image).isNullOrEmpty()) {
            data?.image.let {
                if (it != null) {
                    SessionManager.saveAuthImage(this,it)
                }
            }
        }
    }
    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)

    }
//    private fun navigateToSuperAdminHome() {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//        startActivity(intent)
//
//    }
//    private fun navigateToAdminHome() {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//        startActivity(intent)
//
//    }
//    private fun navigateToEmployeeHome() {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//        startActivity(intent)
//
//    }

    private fun processError(msg: String?) {
        showToast("Error: $msg")


    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

}