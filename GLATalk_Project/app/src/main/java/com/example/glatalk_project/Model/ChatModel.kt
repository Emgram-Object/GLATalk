package com.example.glatalk_project.Model
//
//import com.example.glatalk_project.network.ApiServer
//import com.example.glatalk_project.network.data.response.ChatResponse
//import com.example.glatalk_project.network.data.response.PapagoResponse
//import io.reactivex.Single
//
//class ChatModel {

//    fun translation(chatData:ChatData): Single<PapagoResponse>{
//        return ApiServer.API.translation(chatData).toStirng()
//    }
//    //여기 API부분 어떻게 수정해야할지 모르겠습니다 저는 빡대가리입니다
//    fun chat_list(room_id: String): Single<ChatResponse> {
//        return ApiServer.API.chat_list(room_id).toSingle()
//    }
//
//
//
//    fun chatList(room_id: String){
//        //채팅방 리스트 방id로 구분해서 정렬하는 거
//    }

//}
//
//class ChatData {
//    var source_lang: String = ""         //요청 언어
//    var source_text: String = ""    //요청 텍스트
//    var target_lang: String? = null         //번역 언어
//    var target_text: String = ""    //번역 텍스트
//    //var text: String = ""          //요청 메세지(번역 api 파라미터)
//    var sender_id: String? = ""        //보내는 사람 id
//    var receiver_id: String? = ""      //받는 사람 id
//    var sender_user_type: String? = ""   //관광객or가이드
//    var receiver_user_type: String? = "" //관광객or가이드
//    var msg_dt: String = ""            //메세지 날짜
//    var room_id: String = ""           //방 id
//    var room_member_cnt: Int?=null //인원수
//}
//
//class Chat {
//    val chat_active: Boolean = false
//    val chat_list: ArrayList<ChatData> = ArrayList()
//}
//
//data class InitData(
//        val sender_id: String,
//        val receiver_id: String,
//        val room_id: String
//)
//
//data class TransData(
//        val source_lang: String,
//        val target_lang: String,
//        val target_text: String
//)

//fun translation(chatData: ChatData){
//
//}