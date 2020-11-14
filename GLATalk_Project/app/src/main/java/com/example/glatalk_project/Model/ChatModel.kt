package com.example.glatalk_project.Model

import com.example.glatalk_project.network.data.response.ChatResponse
import com.example.glatalk_project.network.data.response.PapagoResponse
import io.reactivex.Single

class ChatModel {
    fun translation(chatData:ChatData): Single<PapagoResponse>{
        return ApiServer.API.translation(chatData).toStirng()
    }
    fun chat_list(room_id: String): Single<ChatResponse> {
        return MainApiServer.API.chat_list(room_id).toSingle()
    }

}

class ChatData {
    var source: String = ""         //요청 언어
    var source_text: String = ""    //요청 텍스트
    var target: String? = null         //번역 언어
    var target_text: String = ""    //번역 텍스트
    //var text: String = ""          //요청 메세지(번역 api 파라미터)
    var sender: String? = ""        //보내는 사람 id
    var receiver: String? = ""      //받는 사람 id
    var sender_type: String? = ""   //사용자/기사 여부 -  NM
    var receiver_type: String? = "" //사용자/기사 여부 -  DR
    var msg_dt: String = ""            //메세지 날짜
    var room_id: String = ""           //방 id
}

class Chat {
    val chat_active: Boolean = false
    val chat_list: ArrayList<ChatData> = ArrayList()
}

data class InitData(
        val sender: String,
        val receiver: String,
        val room_id: String
)

data class TransData(
        val source: String,
        val target: String,
        val target_text: String
)