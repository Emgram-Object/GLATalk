package com.example.glatalk_project.constant

object C {
    object Preference {
        const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        const val KEY_IS_AUTO_LOGIN = "KEY_IS_AUTO_LOGIN"
        const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    } //Preference를 위한 객체 (싱글톤) , 객체 접근 시점에 객체가 생성
}

object languageCode {
    val korLanguageCode: String = "ko"
    val engLanguageCode: String = "en"
    val jpnLanguageCode: String = "ja"
    val chLanguageCode: String = "zh"
}