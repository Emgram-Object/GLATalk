package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatAdapter
import com.example.glatalk_project.core.data.ChatData
import com.example.glatalk_project.core.helper.LocaleHelper
import com.example.glatalk_project.core.util.ChatManager
import com.example.glatalk_project.network.ApiServer
import io.socket.client.IO
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Socket
import java.util.*

class ChatActivity : AppCompatActivity() {

    private var roomName = ""
    private var receiver_id = ""
    var chatList = arrayListOf<ChatData>()
    val chatadapter = ChatAdapter(chatList)

    private var isConnected = false

//    private var mSocket: Socket =  IO.socket("http://211.215.19.77:3333/")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chat_rv.adapter = chatadapter

        chat_send_iv.setOnClickListener {
            sendMessage()
        }

        back_btn.setOnClickListener {
            finish()
        }

        //소켓
        ChatManager.instance.setChatListener(chatListener)

    }

    fun sendMessage() {
        val currentlang = LocaleHelper.getLanguage(this) //실제로 쓸거
//    val currentlang = Locale.getDefault().getLanguage() //테스트용
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val item = ChatData(
                currentlang,
                chat_input_et.text.toString(),
                "ko",
                "translation",
                "",
                receiver_id,
                "tourist",
                "guide",
                roomName,
                df.format(Date(System.currentTimeMillis())),
                "")

        if(item.source_text != "") {
            chatadapter.addItem(item)
            chat_input_et.setText("")
        }
        chat_rv.scrollToPosition(chatadapter.getChatSize() - 1)

//        val chatData = ChatModel
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
    private var chatListener = object: ChatManager.OnChatListener {
        override fun onConnected() {
            isConnected = true
        }

        override fun onReceive(chat: ChatData) {
            runOnUiThread {
                //chat.type = C.MessageType.CHAT_OTHER.index
                chatadapter.addChat(chat)
                chat_rv.scrollToPosition(chatadapter.getChatSize() - 1)
            }
        }

        override fun onConnectError() {
            isConnected = false
        }

        override fun onDisconnect() {
            isConnected = false
        }
    }

    override fun onDestroy() {
        ChatManager.instance.release()
        super.onDestroy()
    }
}