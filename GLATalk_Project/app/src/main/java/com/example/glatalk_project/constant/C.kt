package com.example.glatalk_project.constant

object C {
    object Preference{
        const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        const val KEY_IS_AUTO_LOGIN = "KEY_IS_AUTO_LOGIN"
        const val KEY_USER_ID = "KEY_USER_ID"
        //const val PUSH_SETTING_CHECKED = "PUSH_SETTING_CHECKED"
        const val PERMISSION_SETTING_CHECKED = "PERMISSION_SETTING_CHECKED"
        const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        const val SEARCH_PREF = "SEARCH_PREF"
        const val USER_NO = "USER_NO"
    } //Preference를 위한 객체 (싱글톤) , 객체 접근 시점에 객체가 생성
}