package com.example.glatalk_project.Model

import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.response.ChatResponse
import com.example.glatalk_project.network.data.response.PapagoResonse
import retrofit2.Callback

object ChatDAO{

    fun translation(chatData: ChatData, callback: Callback<PapagoResonse>){
        ApiServer.network.translation(chatData).enqueue(callback)
    }

    fun chat_list(room_id: String, callback: Callback<ChatResponse>){
        ApiServer.network.chat_list(room_id).enqueue(callback)
    }
}