package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatAdapter
import com.example.glatalk_project.core.data.ChatData
import com.example.glatalk_project.core.data.ChatModel
import com.example.glatalk_project.core.helper.LocaleHelper
import com.example.glatalk_project.network.ApiServer
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ChatActivity : AppCompatActivity() {

    //    val currentlang = LocaleHelper.getLanguage(this)
    val currentlang = Locale.getDefault().getLanguage() //테스트용
    private var roomName = ""
    private var receiver_id = ""
    var chatList = arrayListOf<ChatModel>()
    val chatadapter = ChatAdapter(chatList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val adapter = ChatAdapter(chatList)
        chat_rv.adapter = adapter

        chat_send_iv.setOnClickListener {

            sendMessage()
        }

//        val retrofit = Retrofit.Builder()
//                .baseUrl("http://211.215.19.77:3333/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        val api = retrofit.create(ApiServer::class.java)


    }

    fun sendMessage() {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        val chatData = ChatModel
        val item = ChatModel(
                currentlang,
                chat_input_et.text.toString(),
                "ko",
                "",
                "",
                receiver_id,
                "tourist",
                "guide",
                roomName,
                df.format(Date(System.currentTimeMillis())))

        chatadapter.addItem(item)
        chatadapter.notifyDataSetChanged()
        chat_input_et.setText("")

//        chatData.source_lang = currentlang  //현재 앱 설정언어
//        chatData.target_lang = "ko"    //번역 언어
//        chatData.source_text = chat_input_et.text.toString()
////            chatData.sender_id = //추가예정
//        chatData.receiver_id = receiver_id
//        chatData.sender_user_type = "tourist" //이거 하드코딩이야?
//        chatData.receiver_user_type = "guide"
//        chatData.room_id = roomName
//        chatData.msg_dt = df.format(Date(System.currentTimeMillis()))

//        chatadapter.addChat(item)
//        chat_rv.scrollToPosition(chatadapter.getChatSize() - 1)

    }
}