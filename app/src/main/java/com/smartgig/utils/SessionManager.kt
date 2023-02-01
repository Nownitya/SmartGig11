package com.smartgig.utils

import android.content.Context
import android.content.SharedPreferences
import com.smartgig.R
import com.smartgig.network.Api.AuthInterceptor

object SessionManager {
    const val USER_TOKEN = "USER_TOKEN"
    const val USER_ID= "USER_ID"
    const val USER_NAME= "USER_NAME"
    const val USER_ROLE= "USER_ROLE"
    const val USER_IMAGE = "USER_IMAGE"
    const val USER_DESIGNATION = "USER_DESIGNATION"




//    fun saveAuthToken(context: LoginFragment, token: String)
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)

    }

    private fun saveString(context: Context, key: String, value: String) {
        val sPrefs: SharedPreferences =
            context.getSharedPreferences("user_token", Context.MODE_PRIVATE)
        val editor = sPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getToken(context: Context): String? {
        val prefs:SharedPreferences = context.getSharedPreferences("USER_TOKEN", Context.MODE_PRIVATE)
        return prefs.getString(USER_TOKEN,null)


    }

//    fun getToken(context: Context):String? {
//        return getString(context, USER_TOKEN)
//    }
//    private fun getString(context: Context, key: String): String? {
//        val sPrefs: SharedPreferences =
//            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
//        return sPrefs.getString(USER_TOKEN, null)
//    }

    fun clearData(context: Context) {
        val editor =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()

    }
//    fun saveAuthData(context: Context, id: String, name: String, role: String)  {
//        saveAuthString(context, USER_ID,id, USER_NAME,name,USER_ROLE,role)
//
//    }

//    private fun saveAuthString(context: Context, userId: String, id: String, userName: String, name: String, userRole: String, role: String) {
//        val sPrefUData: SharedPreferences =
//            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
//        val editor = sPrefUData.edit()
//        editor.putString(userId, id)
//        editor.putString(userName, name)
//        editor.putString(userRole, role)
//        editor.apply()
//
//    }

    // Name
    fun saveAuthName(context: Context, name: String) {
        saveNameString(context, USER_NAME ,name)
    }

    private fun saveNameString(context: Context, userName: String, name: String) {
        val shaPref: SharedPreferences =
            context.getSharedPreferences("user_name", Context.MODE_PRIVATE)
        val editor = shaPref.edit()
        editor.putString(userName, name)
        editor.apply()
    }
    fun getName(context: Context): String? {
        val nameShaPref: SharedPreferences = context.getSharedPreferences("USER_NAME", Context.MODE_PRIVATE)
        return nameShaPref.getString(USER_NAME,null)
    }

    // ID
    fun saveAuthID(context: Context, id: String) {
        saveIdString(context, USER_ID,id)
    }

    private fun saveIdString(context: Context, userId: String, id: String) {
        val shaPref: SharedPreferences =
            context.getSharedPreferences("user_id", Context.MODE_PRIVATE)
        val editor = shaPref.edit()
        editor.putString(userId, id)
        editor.apply()
    }
    fun getId(context: Context): String? {
        val nameShaPref: SharedPreferences = context.getSharedPreferences("USER_ID", Context.MODE_PRIVATE)
        return nameShaPref.getString(this.USER_ID,null)
    }

    // Role
    fun saveAuthRole(context: Context, role: String) {
        saveRoleString(context, USER_ROLE,role)

    }

    private fun saveRoleString(context: Context, userRole: String, role: String) {
        val shaPref: SharedPreferences =
            context.getSharedPreferences("user_role", Context.MODE_PRIVATE)
        val editor = shaPref.edit()
        editor.putString(userRole, role)
        editor.apply()
    }

    fun getRole(context: Context): String? {
        val roleShaPref: SharedPreferences = context.getSharedPreferences("USER_ROLE", Context.MODE_PRIVATE)
        return roleShaPref.getString(USER_ROLE,null)
    }

    // Designation
    fun saveAuthDeg(context: Context, deg: String) {
        saveRoleString(context, USER_DESIGNATION,deg)

    }

    private fun saveDegString(context: Context, userDeg: String, deg: String) {
        val shaPref: SharedPreferences =
            context.getSharedPreferences("user_designation", Context.MODE_PRIVATE)
        val editor = shaPref.edit()
        editor.putString(userDeg, deg)
        editor.apply()
    }

    fun getDeg(context: Context): String? {
        val roleShaPref: SharedPreferences = context.getSharedPreferences("USER_DESIGNATION", Context.MODE_PRIVATE)
        return roleShaPref.getString(USER_DESIGNATION,null)
    }

    //  Image
    fun saveAuthImage(context: Context, image: String) {
        saveImageUrl(context, USER_IMAGE, image)
    }

    private fun saveImageUrl(context: Context, userImage: String, image: String) {
        val sharPref: SharedPreferences =
            context.getSharedPreferences("user_image", Context.MODE_PRIVATE)
        val editor = sharPref.edit()
        editor.putString(userImage, image)
        editor.apply()

    }

    fun getImage(context: Context): String? {
        val imgShaPref: SharedPreferences =
            context.getSharedPreferences("user_image", Context.MODE_PRIVATE)
        return imgShaPref.getString(USER_IMAGE, null)

    }


//    fun getAuthData(context: Context, id: Int, name: String, role: String): String? {
//        val sPref:SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
//        return sPref.getString(this.USER_NAME, this.USER_ROLE, this.USER_ID,null)
//    }

}


