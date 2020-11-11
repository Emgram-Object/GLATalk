package com.example.glatalk_project.network.error

import android.content.Context
import com.example.glatalk_project.R
import com.example.glatalk_project.network.ServerResult

object AppError {
    fun getMessage(context: Context, error: Exception): String {
        if(error is Base) {
            return error.getMessage(context)
        }
        return error.localizedMessage
    }

    open class Base (message: String? = "",
                      cause: Throwable? = null) : Exception(message, cause){
        open fun getMessage(context: Context): String {
            return "에러 발생"
        }

        open fun getResultCode(): String {
            return "-1"
        }

    }

    /**
     * status code 200 외의 값인 경우 처리(300 ~ 500) 메세지 정의 필요.
     */
    class Network(val httpCode: Int, cause: Throwable? = null) : Base("", cause) {
        private val prefix = "net_network_"

        override fun getMessage(context: Context): String {
            try {
                val ref = R.string::class.java
                val field = ref.getField(prefix + httpCode)
                val resId = field.getInt(null)
                return context.getString(resId)
            } catch (e: Exception) {
                return "서버 통신 실패"
            }
        }
    }

    /**
     * 200 ok이면서 서버 에러 메세지에 따라 알럿창 띄우는 경우.
     */
    class Server(val response: ServerResult, cause: Throwable? = null) : Base("", cause) {
        private val prefix = "net_server_"

        override fun getMessage(context: Context): String {
            try {
//                val ref = R.string::class.java
//                val code = response.resultCode()
//                val field = ref.getField(prefix + code)
//                val resId = field.getInt(null)
//                return context.getString(resId)
                return response.errorMessage() ?: "서버 통신 실패"
            } catch (e: Exception) {
                val msg = response.errorMessage()
                return msg ?: "서버 통신 실패"
            }
        }

        override fun getResultCode(): String {
            return response.resultCode()
        }
    }
}