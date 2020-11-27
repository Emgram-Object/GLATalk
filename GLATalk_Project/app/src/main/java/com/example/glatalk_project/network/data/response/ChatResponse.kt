package com.example.glatalk_project.network.data.response

import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.Data.ChatHistory

class ChatResponse: BaseArrayResponse() {
    var body: ArrayList<ChatHistory>? = null
}