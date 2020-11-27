package com.example.glatalk_project.Model

import android.util.Log
import com.example.glatalk_project.Adapter.ChatAdapter
import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.Data.ChatHistory
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.BaseArrayResponse
import com.example.glatalk_project.network.data.response.ChatResponse
import com.example.glatalk_project.network.data.response.PapagoResonse
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.collections.ArrayList

object ChatDAO{

    fun translation(chatData: ChatData, callback: Callback<PapagoResonse>){
        ApiServer.network.translation(chatData).enqueue(callback)
    }

    fun chat_delete(room_id: String, callback: Callback<BaseArrayResponse>){
        ApiServer.network.chat_delete(room_id).enqueue(callback)
    }

    fun chat_list(room_id: String, callback: Callback<ChatResponse>){
        ApiServer.network.chat_list(room_id).enqueue(callback)
    }

    fun getchatList(room_id: String, chatList: ArrayList<ChatData>, chatAdapter: ChatAdapter) {
        chat_list(room_id, callback = object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body: ArrayList<ChatHistory>? = result?.body

                try {
                    chatAdapter.setChatList(chatList)

                    for (i:Int in 0 until body!!.size) {
                        var chat: ChatHistory = body[i]

                        var chatData = ChatData()
                        chatData.source = chat.source_lang
                        chatData.source_text = chat.source_text
                        chatData.target = chat.target_lang
                        chatData.target_text = chat.target_text
                        chatData.sender = chat.sender_id
                        chatData.receiver = chat.receiver_id
                        chatData.sender_type = chat.sender_user_type
                        chatData.receiver_type = chat.receiver_user_type
                        chatData.msg_dt = chat.msg_dt
                        chatList.add(chatData)
                    }

                    chatAdapter.notifyDataSetChanged()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Log.d("ChatList", "성공")
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })
    }

    fun ChatDelete(room_id: String){
        chat_delete(room_id, callback = object : Callback<BaseArrayResponse>{
            override fun onResponse(call: Call<BaseArrayResponse>, response: Response<BaseArrayResponse>) {
                Log.d("Chat_Delete", "성공")
            }

            override fun onFailure(call: Call<BaseArrayResponse>, t: Throwable) {
                Log.d("Chat_Delete", "실패")
            }
        })

    }

    fun getTranslation(chatData: ChatData) {
        translation(chatData, callback = object : Callback<PapagoResonse> {
            override fun onResponse(call: Call<PapagoResonse>, response: Response<PapagoResonse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body = result?.body

                Log.d("translation Body", body.toString())
                Log.d("translation", "성공")
            }

            override fun onFailure(call: Call<PapagoResonse>, t: Throwable) {
                Log.d("translation", "실패")
            }
        })
    }
}