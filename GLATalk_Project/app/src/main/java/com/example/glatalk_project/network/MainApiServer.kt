package com.example.glatalk_project.network


import com.example.glatalk_project.BuildConfig
import com.example.glatalk_project.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainApiServer : BaseNetwork () {
    override fun createRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()  //okhttp3 로그 출력용 인터셉터
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().apply {
            writeTimeout(10, TimeUnit.SECONDS) // default
            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
            addInterceptor(TMApiInterceptor())
        }.build()

        return Retrofit.Builder()
//             .baseUrl("http://211.215.19.76:1013/api")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    companion object {
        private var _api: TMApiProtocol? = null

        val API: TMApiProtocol
            get() {
                if (_api == null) {
                    val network = MainApiServer()
                    _api = network.retrofit.create(
                            TMApiProtocol::class.java)
                }
                return _api!!
            }
    }
}