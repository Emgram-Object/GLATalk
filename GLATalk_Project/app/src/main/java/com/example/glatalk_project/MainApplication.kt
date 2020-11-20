package com.example.glatalk_project

import android.content.Context
import android.os.Process
import androidx.multidex.MultiDexApplication
import com.example.glatalk_project.util.SharedPreferencesUtil
import java.lang.ref.WeakReference
import java.sql.RowId

class MainApplication: MultiDexApplication() {
    companion object{
        private var _application:WeakReference<MainApplication>?=null

        lateinit var prefs : SharedPreferencesUtil

        val application:MainApplication?
            get() {
                if(_application != null){
                    return _application!!.get()
                }
                return null
            }


    }

    override fun onCreate(){
        prefs = SharedPreferencesUtil(applicationContext)
        super.onCreate()
        _application = WeakReference(this)

    }

    fun setUncaughtExceptionHandler() {
        val defaultHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->

            defaultHandler?.uncaughtException(t, e)
            Process.killProcess(Process.myPid())
            System.exit(0)
        }
    }


}

