package com.example.glatalk_project.network

import okhttp3.OkHttpClient

import retrofit2.Retrofit

abstract class BaseNetwork {
    var retrofit: Retrofit

    init {
        retrofit = createRetrofit()
    } // Retrofit 객체 초기화

    abstract fun createRetrofit():Retrofit

}//추상클래스, 다른데서 사용하려면 저 abstract fun createRetrofit:Retrofit을 다시 재구현해야함. (오버라이딩을 통한 재정)

interface ServerResult {
    fun isSuccess(): Boolean
    fun resultCode(): String
    fun errorMessage(): String?
//    fun token(): String?
} //interface를 선 (interface는 추상클래스와 달리 상태 저장 불가)



