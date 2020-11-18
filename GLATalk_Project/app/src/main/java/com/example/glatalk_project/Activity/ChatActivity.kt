package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.ChatDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatAdapter
import com.example.glatalk_project.core.data.ChatData
import com.example.glatalk_project.core.helper.LocaleHelper
import com.example.glatalk_project.core.util.ChatManager
import com.example.glatalk_project.network.data.request.ChatRequset
import com.example.glatalk_project.network.data.response.ChatResponse
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChatActivity : AppCompatActivity() {

    private var chatDAO = ChatDAO
    private var roomName = ""
    private var receiver_id = ""
    private var sender_id = ""
    var chatList = arrayListOf<ChatData>()
    val chatadapter = ChatAdapter(chatList)
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        roomName = intent.getStringExtra("reserve_id") ?: ""
        receiver_id = intent.getStringExtra("receiver_id") ?: ""

        chat_rv.adapter = chatadapter
        chatDAO.chat_list(roomName, callback = object: Callback<ChatResponse>{
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                Log.d("ChatList", "성공")
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })

        chat_send_iv.setOnClickListener {
            sendMessage()
        }

        back_btn.setOnClickListener {
            finish()
        }

        //레트로핏



        //소켓
        ChatManager.instance.setChatListener(chatListener)
        ChatManager.instance.init(sender_id,receiver_id, roomName)



    }

    fun sendMessage() {
        val currentlang = LocaleHelper.getLanguage(this)
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val chatData = ChatData(
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

        if(chatData.source_text != "") {
//            chatadapter.addItem(chatData)
            ChatManager.instance.sendMessage(chatData)
        }
//        chat_rv.scrollToPosition(chatadapter.getChatSize() - 1)

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

//    fun Translation(){
//        ChatDAO.translation(ChatData())
//    }

    private var chatListener = object: ChatManager.OnChatListener {
        override fun onConnected() {
            isConnected = true
        }

        override fun onReceive(chat: ChatData) {
            runOnUiThread {
                //chat.type = C.MessageType.CHAT_OTHER.index
                chatadapter.addChat(chat)
                chat_rv.scrollToPosition(chatadapter.getChatSize() - 1)
                chat_input_et.setText("")
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