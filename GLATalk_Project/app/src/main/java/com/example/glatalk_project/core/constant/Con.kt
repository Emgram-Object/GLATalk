package com.example.glatalk_project.core.constant

object Con {
    object Preference {
        const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    }
    enum class NationalCode(val country_cd: String, val country_nm: String) {
        ko("ko", MainApplication.getString(R.string.korea)),
        en("en", MainApplication.getString(R.string.country_america)),
        zh("zh", MainApplication.getString(R.string.country_china)),
        ja("ja", MainApplication.getString(R.string.country_japan));
    }
}