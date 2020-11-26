package com.example.glatalk_project.network.data.response

import com.example.glatalk_project.Data.ChatRoom
import java.util.ArrayList

class HomeResponse: BaseArrayResponse() {
    var body: ArrayList<ChatRoom>? = null
}