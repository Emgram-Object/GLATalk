package com.example.glatalk_project.network

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.BaseMenuPresenter
import com.example.glatalk_project.Model.UserDAO
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServer {
    companion object {

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://211.215.19.77:1102/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var login: networkInterface = retrofit.create(networkInterface::class.java)
//
//        private var _api: networkInterface?=null
//
//        val API: networkInterface
//            get(){
//                if (_api == null) {
//                    _api = retrofit.create(networkInterface::class.java)
//
//                }
//                return _api!!
//            }
    }
}



