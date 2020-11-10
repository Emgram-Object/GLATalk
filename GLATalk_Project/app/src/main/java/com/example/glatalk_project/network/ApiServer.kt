package com.example.glatalk_project.network

import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServer {

    var login:networkInterface= retrofit.create(networkInterface::class.java)

    companion object{
        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://211.215.19.76:1102/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}