package com.example.glatalk_project.Data

//class ChatData() {
//    var source_lang: String = ""         //요청 언어
//    var source_text: String = ""    //요청 텍스트
//    var target_lang: String? = null         //번역 언어
//    var target_text: String = ""    //번역 텍스트
//    //var text: String = ""          //요청 메세지(번역 api 파라미터)
//    var sender_id: String? = ""        //보내는 사람 id
//    var receiver_id: String? = ""      //받는 사람 id
//    var sender_user_type: String? = ""   //가이드/관광객 여부 -  NM
//    var receiver_user_type: String? = "" //가이드/관광객 여부 -  DR
//    var msg_dt: String = ""            //메세지 날짜
//    var room_id: String = ""           //방 id
//    var room_member_cnt:String = ""   //방 인원수 체크
//}

data class InitData(
        var sender: String,
        var receiver: String,
        var room_id: String
)

data class TransData(
        var source: String = "",          //요청언어
        var target: String = "",          //번역언어
        var target_text: String = ""      //번역메시지
)

//class Chat(
//        var chat_active: Boolean = false,
//        var chat_list: ArrayList<ChatData> = ArrayList()) {}


class ChatData(
    var source: String = "",             //요청 언어
    var source_text: String = "",             //요청 텍스트
    var target: String? = "",           //번역 언어
    var target_text: String = "",             //번역 텍스트
    var sender: String? = "",              //보내는 사람 id
    var receiver: String? = "",            //받는 사람 id
    var sender_type: String? = "",       //가이드/관광객 여부 -
    var receiver_type: String? = "",     //가이드/관광객 여부 -
    var msg_dt: String = "",                  //메세지 날짜
    var room_id: String = "",                 //방 id
    var room_member_cnt: String = ""   //방 인원수 체크
)

//class ChatHistory(
//        var source_lang: String = "",             //요청 언어
//        var source_text: String = "",             //요청 텍스트
//        var target_lang: String? = "",           //번역 언어
//        var target_text: String = "",             //번역 텍스트
//        var sender_id: String? = "",              //보내는 사람 id
//        var receiver_id: String? = "",            //받는 사람 id
//        var sender_user_type: String? = "",       //가이드/관광객 여부 -
//        var receiver_user_type: String? = "",     //가이드/관광객 여부 -
//        var msg_dt: String = "",                  //메세지 날짜
//)