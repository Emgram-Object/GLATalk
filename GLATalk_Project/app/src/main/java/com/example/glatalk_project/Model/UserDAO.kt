package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.util.PreferenceUtil

class UserDAO {
    var accessToken: String? =""
    var user_id:String?=""
    var country_cd:String =""
    var language_code:String =""
    set(lang) {
        PreferenceUtil.putStringSync(C.Preference.SELECTED_LANGUAGE,lang)
        field = lang
        Log.d("UserDAO","lang: "+lang)
    }
    init {
        load()
    }
    private fun load(){
        language_code = PreferenceUtil.getString(C.Preference.SELECTED_LANGUAGE)
    }

    companion object{
        private var _instance:UserDAO?=null
        val instance:UserDAO
        get() {
            if (_instance==null){
                _instance = UserDAO()
            }
            return _instance!!
        }
    }
}