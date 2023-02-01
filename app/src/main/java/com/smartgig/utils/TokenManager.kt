package com.smartgig.utils

import android.content.Context
import android.content.SharedPreferences
import com.smartgig.constant.Constant.PREF_USER_TOKEN
import com.smartgig.constant.Constant.USER_DESIGNATION
import com.smartgig.constant.Constant.USER_ID
import com.smartgig.constant.Constant.USER_NAME
import com.smartgig.constant.Constant.USER_TOKEN

class TokenManager (context: Context){
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_USER_TOKEN, Context.MODE_PRIVATE)

    fun saveAuthTokens(token: String) {
        saveString(USER_TOKEN)

    }

    private fun saveString(Token: String) {

        val editor = prefs.edit()
        editor.putString(USER_TOKEN, null)
        editor.apply()
    }
    fun getToken(): String? {
        return prefs.getString(USER_TOKEN,null)
    }

    // Name
    fun saveAuthName(name: String) {
        saveName(USER_NAME)
    }

    private fun saveName(userName: String) {

    }

    // ID
    fun saveAuthID(context: Context, id: String) {
        saveIdString(context, SessionManager.USER_ID,id)
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
        return nameShaPref.getString(USER_ID,null)
    }

    // Role
    fun saveAuthRole(context: Context, role: String) {
        saveRoleString(context, SessionManager.USER_ROLE,role)

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
        return roleShaPref.getString(SessionManager.USER_ROLE,null)
    }

    // Designation
    fun saveAuthDeg(context: Context, deg: String) {
        saveRoleString(context, SessionManager.USER_DESIGNATION,deg)

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
        saveImageUrl(context, SessionManager.USER_IMAGE, image)
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
        return imgShaPref.getString(SessionManager.USER_IMAGE, null)

    }
}
