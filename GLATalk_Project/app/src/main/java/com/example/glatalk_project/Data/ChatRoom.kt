package com.example.glatalk_project.Data

class ChatRoom(
        var tourist_name: String = "",
        var tourist_info: String = "",
        var last_chat_time: String = "",
        var new_msg: Boolean = false,
        var room_id: String = "",
        var guide_name: String = "",
        var guide_info: String = "",
        var guide_id: String = "",
        var guide_time: String = "",
        var chat_yn: Boolean = false
) {
    override fun toString(): String {
        return "ChatRoom(tourist_name='$tourist_name', tourist_info='$tourist_info', last_chat_time='$last_chat_time', new_msg=$new_msg, room_id='$room_id', guide_name='$guide_name', guide_info='$guide_info', guide_id='$guide_id', guide_time='$guide_time', chat_yn=$chat_yn)"
    }
}