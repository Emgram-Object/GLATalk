package com.example.glatalk_project.util

import android.content.Context
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.R

object SharedPreferenceUtil {

    //autoLoginFlag -> autoLogin을 할지 말지 체크해주는 거 (type: boolean)
    //key -> autoLogin 시 필요한 키 값 (type: string)
    //autoLoginFlag로 먼저 사용자가 자동로그인을 할지 말지를 저장하고 자동록인을 한다고 하면 key에 특정 값을 저장하고 그 값이 null이 아니고 저장시의 값과 동일하다면 통과
    // 로그아웃시 플래그는 false(default)로 초기화 되며 key값은 null로 초기화.

    //firstLanguage액티비티에서 저장한 언어코드를 따로 저장해줌. -> 언어 설정에서 변경시 거기 값으로 다시 putString 해줌 (type: string)

    //putStrings
    fun putString(key: String,value: String?){
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference),Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putString(key,value)
            edit.apply()
        }
    }


    //getString
    fun getString(key: String):String =
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference),Context.MODE_PRIVATE)
            preferences.getString(key,"")
    }?:""

    fun getString(key: String, default: String) : String =
            MainApplication.application?.let {
                val preference = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
                preference.getString(key, default)
            } ?: default

    //putBoolean -> 대이터를 기입
    fun putBoolean(key: String, value: Boolean) {
        MainApplication.application?.let {
            val preferences = it.getSharedPreferences(it.getString(R.string.preference), Context.MODE_PRIVATE)
            val edit = preferences.edit()
            edit.putBoolean(key, value)
            edit.apply()
        }
    }
    //getBoolean입 -> 데이터를 가져옴
    fun getBoolean(key:String,default: Boolean):Boolean =
            MainApplication.application?.let {
                val preferences = it.getSharedPreferences(it.getString(R.string.preference),Context.MODE_PRIVATE)
                preferences.getBoolean(key,default)
            }?:default
}
