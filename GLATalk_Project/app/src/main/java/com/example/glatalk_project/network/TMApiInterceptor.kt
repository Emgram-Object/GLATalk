package com.example.glatalk_project.network

import android.util.Log
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R

import okhttp3.Interceptor
import okhttp3.Response


class TMApiInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        MainApplication.application?.let {
            if (!request.url().host().contains(it.getString(R.string.base_url))) {
                return chain.proceed(request)
            } else {
                val token = UserDAO.instance.accessToken
                Log.d("App Token", token ?: "")
                if (token != null && token.isNotEmpty()) {
                    builder.addHeader("Authorization", token)
                }
                builder.addHeader("Accept-Language", UserDAO.instance.language_code)

                return chain.proceed(builder.build())
            }
        }