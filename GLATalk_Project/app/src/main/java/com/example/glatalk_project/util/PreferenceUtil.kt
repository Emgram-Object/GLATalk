package com.example.glatalk_project.util

import android.content.Context
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.R

object PreferenceUtil {
//    fun putString(key: String, value: String?) {
//        MainApplication.application?.let {
//            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//            val edit = preferences.edit()
//            edit.putString(key, value)
//            edit.apply()
//        }
//
//    }
//
//  //SharedPreference는 간단한 데이터를 저장하기 위함(어플이 삭제되기 전까지 유지)
//
//    fun putStringSync(key: String, value: String?) {
//        MainApplication.application?.let {
//            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//            val edit = preferences.edit()
//            edit.putString(key, value)
//            edit.commit()
//        }
//    }
//
//    fun getString(key: String) : String =
//            MainApplication.application?.let {
//                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//                preference.getString(key, "")
//            } ?: "" //AccessToken, Language, UserId 에서 사용
//
//    fun getString(key: String, default: String) : String =
//            MainApplication.application?.let {
//                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//                preference.getString(key, default)
//            } ?: default //Locale Helper에서 언어 설정한 값을 앱 삭제 전까지 가지고 있기 위한 부분인 거같음
//
//    fun putBoolean(key: String, value: Boolean) {
//        MainApplication.application?.let {
//            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//            val edit = preferences.edit()
//            edit.putBoolean(key, value)
//            edit.apply()
//        } //자동 로그인 on/off 값을 저장할 때 사용
//    }
//
//    fun getBoolean(key: String, default: Boolean) : Boolean =
//            MainApplication.application?.let {
//                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
//                preference.getBoolean(key, default)
//            } ?: default //User DAO에서 자동 로그인 관련된 부분 관리할 때 사용 , Base Network에서 reponse받을 때 자동로그인값이 있는지의 판단
}