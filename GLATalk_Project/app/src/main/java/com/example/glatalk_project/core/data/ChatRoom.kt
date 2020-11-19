package com.example.glatalk_project.core.data

data class ChatRoom(val tourist_name: String,
                    val tourist_info: String,
                    val last_chat_time: String,
                    val new_msg: Boolean,
                    val room_id: String,
                    val guide_name: String,
                    val guide_info: String,
                    val guide_time: String,
                    val chat_yn: Boolean)