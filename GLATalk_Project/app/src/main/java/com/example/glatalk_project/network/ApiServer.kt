package com.example.glatalk_project.network

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.BaseMenuPresenter
import com.example.glatalk_project.Model.UserDAO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServer {
    companion object {

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://211.215.19.77:1102/api/")
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(ApiInterceptor())
                }.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}