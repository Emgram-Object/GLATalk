package com.example.glatalk_project.util

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.preference.PreferenceManager
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.constant.C.Preference.SELECTED_LANGUAGE
import java.util.*

object LocaleHelper {

//    private var SELECTED_LANGUAGE: String = "Locale.Helper.Selected.Language"

    fun onAttach(context: Context): Context{
        var lang:String = getPersistedData(context, Locale.getDefault().getLanguage())
        return setLocale(context, lang)
    }

    fun onAttach(context: Context, defaultLanguage: String): Context{
        var lang: String = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String {
        return getPersistedData(context, Locale.getDefault().getLanguage())
    }

    fun setLocale(context: Context, language: String): Context {
        persist(context, language)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language)
        }

        return updateResourcesLegacy(context, language)
    }

     fun getPersistedData(context: Context, defaultLanguage: String): String {
//        var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        return SharedPreferenceUtil.getString(SELECTED_LANGUAGE, defaultLanguage)!!
    }

    private fun persist(context: Context, language: String) {
//        var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        var editor: SharedPreferences.Editor = preferences.edit()
        SharedPreferenceUtil.putString(SELECTED_LANGUAGE, language)
//        editor.apply()
    }

    private fun getLocale(config: Configuration): Locale {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return config.locales[0]
        } else {
            return config.locale
        }
    }

    fun setLangFlag(have:Boolean){
        SharedPreferenceUtil.putBoolean(C.Preference.LANGUAGE_VALUE,have)
    }
    fun haveLangValue():Boolean = SharedPreferenceUtil.getBoolean(C.Preference.LANGUAGE_VALUE,false)


    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    @SuppressWarnings("deprecation")
    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}