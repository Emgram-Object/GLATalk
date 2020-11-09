package com.example.glatalk_project.util

import android.content.Context
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.R

object PreferenceUtil {
    fun putString(key: String, value: String?) {
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putString(key, value)
            edit.apply()
        }

    }

    fun putStringSync(key: String, value: String?) {
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putString(key, value)
            edit.commit()
        }
    }

    fun getString(key: String) : String =
            MainApplication.application?.let {
                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
                preference.getString(key, "")
            } ?: ""

    fun getString(key: String, default: String) : String =
            MainApplication.application?.let {
                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
                preference.getString(key, default)
            } ?: default

    fun putBoolean(key: String, value: Boolean) {
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putBoolean(key, value)
            edit.apply()
        }

    }

    fun getBoolean(key: String, default: Boolean) : Boolean =
            MainApplication.application?.let {
                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
                preference.getBoolean(key, default)
            } ?: default
}