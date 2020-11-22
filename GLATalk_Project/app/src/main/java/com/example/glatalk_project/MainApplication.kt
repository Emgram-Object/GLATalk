package com.example.glatalk_project
import android.os.Process
import androidx.multidex.MultiDexApplication
import com.example.glatalk_project.util.SharedPreferenceUtil
import java.lang.ref.WeakReference

class MainApplication: MultiDexApplication() {
    companion object{
        private var _application:WeakReference<MainApplication>?=null


        val application:MainApplication?
            get() {
                if(_application != null){
                    return _application!!.get()
                }
                return null
            }


    }

    override fun onCreate(){
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

