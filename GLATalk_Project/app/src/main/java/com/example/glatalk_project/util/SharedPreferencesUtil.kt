package com.example.glatalk_project.util

import android.content.Context
import android.content.SharedPreferences
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.R

object SharedPreferencesUtil {
    fun putBoolean(key:String,value:Boolean){
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putBoolean(key, value)
            edit.apply()
        }
    }
    fun getBoolean(key: String,default: Boolean):Boolean = MainApplication.application?.let {
        val preference = it.getSharedPreferences(it.getString(R.string.preference),Context.MODE_PRIVATE)
        preference.getBoolean(key,default)
    }?:default
}