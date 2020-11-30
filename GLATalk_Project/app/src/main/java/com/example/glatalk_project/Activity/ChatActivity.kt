package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.ChatAdapter
import com.example.glatalk_project.Data.*
import com.example.glatalk_project.Model.ChatDAO.getchatList
import com.example.glatalk_project.Model.ChatDAO.getTranslation
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.util.ChatManager
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : AppCompatActivity() {
    //변수 선언분
    private lateinit var receiver: String
    private lateinit var tourist_info: String
    private lateinit var tourist_name: String
    private lateinit var guide_name: String
    private lateinit var room_id: String
    private val sender = ProfileData.user_email
    private var isConnected = false
    var chatData = ChatData()
    val user_type = ProfileData.user_type
    var chatList = arrayListOf<ChatData>()
    val chatAdapter = ChatAdapter(chatList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

//        Log.d("chat_token", "${TokenData.loginToken}")


        //HomeFragment에서 intent로 값 받아오기
        receiver = intent.getStringExtra("receiver") ?: ""
        tourist_info = intent.getStringExtra("tourist_info") ?: ""
        tourist_name = intent.getStringExtra("tourist_name") ?: ""
        guide_name = intent.getStringExtra("guide_name") ?: ""
        room_id = intent.getStringExtra("room_id").toString()

        //room_id가 null일때 새로 생성
        if (room_id == "null") {
            room_id = ProfileData.user_email + receiver
        }
//        Log.d("Room_id", room_id)

        //채팅내역 리사이클러뷰
        chat_rv.adapter = chatAdapter
        chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)

        //ㅂ유저타입 분기에 따라 보여주는 뷰 정보 처리
        if (ProfileData.user_type.equals("guide")) {
            title_tv.text = tourist_name
            mobil_info.visibility = View.VISIBLE
            mobil_info.text = tourist_info
        } else {
            title_tv.text = guide_name
            mobil_info.visibility = View.GONE
        }


        //채팅내역 레트로핏 통신
        getchatList(room_id, chatList, chatAdapter)

        //소켓 초기화
        ChatManager.instance.setChatListener(chatListener)
        ChatManager.instance.init(sender, receiver, room_id)


        //리스너
        chat_send_iv.setOnClickListener {
            ChatManager.instance.userCount()
            setChatData() //채팅데이터 설정
            getTranslation(chatData)  //설정된 채팅데이터로 번역API 호출 및 소켓통신
            chatData.target_text = TransData().target_text
            sendMessage()

        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun setChatData(): ChatData {
        var receiver_type = ""
        var targetlang = ""
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm")

        if (ProfileData.user_type.equals("tourist")) { //sender타입에 따라 receiver타입 변경
            receiver_type = "guide"
            targetlang = "ko"
        } else {
            receiver_type = "tourist"
            targetlang = tourist_info
        }

        chatData = ChatData()
        chatData.source = LocaleHelper.getLanguage(this)
        chatData.source_text = chat_input_et.text.toString()
        chatData.target = targetlang
        chatData.target_text = ""
        chatData.sender = sender
        chatData.receiver = receiver
        chatData.sender_type = ProfileData.user_type
        chatData.receiver_type = receiver_type
        chatData.msg_dt = df.format(Date(System.currentTimeMillis()))
        chatData.room_id = room_id
        chatData.room_member_cnt = "1"

        return chatData
    }

    fun sendMessage() {
        if (chatData.source_text != "") { //채팅입력창에 빈칸입력안돼게 if문 처리
            chatAdapter.addChat(chatData)
            chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
            chat_input_et.setText("")
            ChatManager.instance.sendMessage(chatData)
//            Log.d("SendMessage", chatData.toString())
        } else {
            null
        }
    }


    private var chatListener = object : ChatManager.OnChatListener {
        override fun onConnected() {
            isConnected = true
        }

        override fun onReceive(chat: ChatData) {
            runOnUiThread {
                //chat.type = C.MessageType.CHAT_OTHER.index
                chatAdapter.addChat(chat)
                chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
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
