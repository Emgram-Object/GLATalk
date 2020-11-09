package com.example.glatalk_project.network

import com.example.glatalk_project.network.error.AppError
import retrofit2.Retrofit

abstract class BaseNetwork {
    protected var retrofit: Retrofit

    init {
        retrofit = createRetrofit()
    }

    abstract fun createRetrofit():Retrofit

}

interface ServerResult {
    fun isSuccess(): Boolean
    fun resultCode(): String
    fun errorMessage(): String?
}

interface CheckValidate{
    fun errorCause(error:AppError)
}