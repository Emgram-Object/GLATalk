package com.example.glatalk_project.network.data.request

data class ChatRequset (
        val source_lang: String,
        val target_lang: String,
        val source_text: String,
        val sender_id: String,
        val receiver_id: String,
        val sender_user_type: String,
        val receiver_user_type: String,
        val room_id: String,
        val room_member_cnt: Number
)