package com.example.glatalk_project.Model

import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.BaseResponse
import retrofit2.Callback

object ChatDAO{

    fun translation(chatData: ChatData, callback: Callback<BaseResponse>){
        ApiServer.network.translation(chatData).enqueue(callback)
    }

    fun chat_list(room_id: String, callback: Callback<BaseResponse>){
        ApiServer.network.chat_list(room_id).enqueue(callback)
    }
}