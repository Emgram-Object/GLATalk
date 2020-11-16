package com.example.glatalk_project.network

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.BaseMenuPresenter
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.TokenData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServer:BaseNetwork() {
     override fun createRetrofit():Retrofit {
         val client = OkHttpClient.Builder().apply {
             addInterceptor(ApiInterceptor())
         }.build()

         return Retrofit.Builder()
                 .baseUrl("http://211.215.19.77:1102/api/")
                 .client(client)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()
     }

    companion object {
        private var _network: networkInterface? = null

        val network: networkInterface
            get() {
                if (_network== null) {
                    val apiServer = ApiServer()
                    _network = apiServer.retrofit.create(
                            networkInterface::class.java)
                }
                return _network!!
            }
    }
}