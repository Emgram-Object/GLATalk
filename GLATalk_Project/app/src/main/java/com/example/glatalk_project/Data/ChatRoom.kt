package com.example.glatalk_project.Data

data class ChatRoom(var tourist_name: String = "",
                    var tourist_info: String = "",
                    var last_chat_time: String = "",
                    var new_msg: Boolean = false,
                    var room_id: String = "",
                    var guide_name: String = "",
                    var guide_info: String = "",
                    var guide_time: String = "",
                    var chat_yn: Boolean = false){
    init {
        new_msg = false
        chat_yn = false
    }
}