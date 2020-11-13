package com.example.glatalk_project.Activity

import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.ChatData
import com.example.glatalk_project.Model.ChatModel
import com.example.glatalk_project.R

class ChatActivity:AppCompatActivity(){
     val layoutResourceId:Int
        get()= R.layout.activity_chat
     //var viewModel: ChatViewModel

    private var roomname=""
    private var receiver = ""
    private var target = ""
   // private var Guide: GuideData? = null

    private var isConnected = false
    private var chatData: ChatData? = null

    override fun initView(){

    }
}
//
//class ChatViewModel(private val model: ChatModel){
//
//}
//livedata를 써야 하나??

