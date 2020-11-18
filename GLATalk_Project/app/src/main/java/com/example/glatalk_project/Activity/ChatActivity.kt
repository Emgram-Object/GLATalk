package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatAdapter
import com.example.glatalk_project.core.data.ChatData
import com.example.glatalk_project.core.helper.LocaleHelper
import com.example.glatalk_project.core.util.ChatManager
import com.example.glatalk_project.network.ApiInterceptor
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.ChatResponse
import com.example.glatalk_project.network.data.response.PapagoResponse
import kotlinx.android.synthetic.main.activity_chat.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Socket
import java.util.*

class ChatActivity : AppCompatActivity() {

    private var roomName = ""
    private var receiver_id = ""
    private var sender_id = ""
    var chatList = arrayListOf<ChatData>()
    val chatadapter = ChatAdapter(chatList)

    private var isConnected = false

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

        //레트로핏
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://211.215.19.77:1102/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        val callBack:CallBack
        chat_list(roomName, , callBack)

        //소켓
        ChatManager.instance.setChatListener(chatListener)
        ChatManager.instance.init(sender_id,receiver_id, roomName)



    }
    interface CallBack{
        fun onSuccess(data: ChatData)
        fun onFailure(t: Throwable)
    }

    fun translation(chatData: ChatData, callback: CallBack){
        ApiServer.network.translation(chatData).enqueue(object: Callback<PapagoResponse>{
            override fun onFailure(call: Call<PapagoResponse>, t: Throwable) {
                callback.onFailure(t)
                Log.d("Failure", "실패")

            }

            override fun onResponse(call: Call<PapagoResponse>, response: Response<PapagoResponse>) {
                callback.onSuccess(chatData)
                Log.d("Success", "성공")
            }

        })
    }

    fun chat_list(room_id: String, chatData:ChatData, callback: CallBack){
        ApiServer.network.chat_list(room_id).enqueue(object: Callback<ChatResponse>{
            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                callback.onFailure(t)
                Log.d("Failure", "실패")

            }

            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                callback.onSuccess(chatData)
                Log.d("Success", "성공")
            }

        })
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