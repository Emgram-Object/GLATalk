package com.example.glatalk_project.network

import com.example.glatalk_project.Data.TokenData
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (TokenData.loginToken != null && TokenData.loginToken.toString().isNotEmpty()){
            builder.addHeader("Authorization", TokenData.loginToken)
        }
        return chain.proceed(builder.build())
    }
}