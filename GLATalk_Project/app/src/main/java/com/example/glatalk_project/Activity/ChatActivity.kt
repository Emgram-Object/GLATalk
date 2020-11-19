package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.ChatDAO
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.ChatAdapter
import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.util.ChatManager
import com.example.glatalk_project.network.data.response.BaseResponse
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChatActivity : AppCompatActivity() {

    private var chatDAO = ChatDAO
    private var room_id = ""
    private var receiver_id = ""
    private var sender_id = ""
    var chatList = arrayListOf<ChatData>()
    val chatAdapter = ChatAdapter(chatList)
    var chatData = ChatData()
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        room_id = intent.getStringExtra("reserve_id") ?: ""
        receiver_id = intent.getStringExtra("receiver_id") ?: ""

        chat_rv.adapter = chatAdapter

        if(ProfileData.user_type.equals("guide")){
            title_tv.text = "관광객 이름"
            mobil_info.visibility = View.VISIBLE
            mobil_info.text = "관광객 정보"
        }else{
            title_tv.text = "가이드 이름"
            mobil_info.visibility = View.GONE
        }

        chatDAO.chat_list("0", callback = object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("ChatList", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })

        chatDAO.translation(chatData, callback = object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("translation", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("translation", "실패")
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
        ChatManager.instance.init(sender_id,receiver_id, room_id)



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
                room_id,
                df.format(Date(System.currentTimeMillis())),
                "")

        if(chatData.source_text != "") {
//            chatadapter.addItem(chatData)
            chatAdapter.addChat(chatData)
            chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
            chat_input_et.setText("")
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
                chatAdapter.addChat(chat)
                chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
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