package com.smartgig.ui.super_admin.home_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.smartgig.R
import com.smartgig.databinding.FragmentHomeBinding
import com.smartgig.network.*
import com.smartgig.utils.NetworkResult
import com.smartgig.utils.SessionManager


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    private lateinit var data : LoginResponse
    private val viewModel by viewModels<HomeViewModel>()

//    val sharedPref: SharedPreferences? = context?.getSharedPreferences(requireContext().getString(R.string.app_name), MODE_PRIVATE)
//    val editor =sharedPref.edit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setId()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

//        setId()


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setId()

        viewModel.userDetailResult.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    showLoading()
                }
                is NetworkResult.Success -> {
                    stopLoading()
                    fetchData(it.data)
                    setData()
                }
                is NetworkResult.Error -> {
                    processError(it.msg)

                }
                else -> {
                    stopLoading()
                }


            }

        }

    }

    private fun setId() {
        val id = SessionManager.getId(requireContext().applicationContext)
        if (id != null) {
            viewModel.empDetails(empId = id)
        }
    }

    private fun showLoading() {
        binding.pgBar.visibility = View.VISIBLE

    }
    private fun stopLoading() {
        binding.pgBar.visibility = View.GONE
    }

    private fun fetchData(data: UserDetailResponse?) {

        if (!(data?.name).isNullOrEmpty()) {
            data?.name.let {
                Toast.makeText(requireContext(), "admin_name ${data?.name}", Toast.LENGTH_SHORT).show()
                if (it != null) {
                    SessionManager.saveAuthName(requireContext(),it)
                }
            }
        }
        if (!(data?.empId).isNullOrEmpty()) {
            data?.empId.let {
                Toast.makeText(requireContext(), "admin_Id ${data?.empId}", Toast.LENGTH_LONG).show()
                if (it != null) {
                    SessionManager.saveAuthID(requireContext(),it)
                }
            }
        }
        if (!(data?.designation).isNullOrEmpty()) {
            data?.designation.let {
                Toast.makeText(requireContext(), "admin_Role ${data?.designation}", Toast.LENGTH_LONG).show()
                if (it != null) {
                    SessionManager.saveAuthRole(requireContext(),it)
                }
            }
        }
        if (!(data?.image).isNullOrEmpty()) {
            data?.image.let {
                if (it != null) {
                    SessionManager.saveAuthImage(requireContext(),it)
                }
            }
        }
    }

    private fun setData() {
        var name = binding.empNameTV
        var designation = binding.empDesignation
        var image = binding.empImageView
//        image.isClickable = true


        val userName = SessionManager.getName(requireContext().applicationContext)
        val userDesignation = SessionManager.getDeg(requireContext().applicationContext)
        val userImage = SessionManager.getImage(requireContext().applicationContext)



        Log.d(TAG,"$userName")
        if (userName != null) {

            name.text = userName
            Log.d(TAG +11,"${userImage.toString()}")
        }

        Log.d(TAG,"$userDesignation")
        if (userDesignation != null) {
            designation.text =  userDesignation
        }

        Glide.with(requireContext().applicationContext)
            .load(userImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)

            .into(image)
    }

    private fun processError(msg: String?) {
        showToast("Error: $msg")


    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

    }

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}