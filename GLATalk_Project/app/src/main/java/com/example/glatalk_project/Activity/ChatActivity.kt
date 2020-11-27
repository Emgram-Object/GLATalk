package com.example.glatalk_project.Activity

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.ChatDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.ChatAdapter
import com.example.glatalk_project.Data.*
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.util.ChatManager
import com.example.glatalk_project.network.data.response.ChatResponse
import com.example.glatalk_project.network.data.response.PapagoResonse
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {
    //변수 선언분
    private var chatDAO = ChatDAO
    private lateinit var receiver: String
    private lateinit var tourist_info: String
    private lateinit var tourist_name: String
    private lateinit var guide_name:String
    private lateinit var room_id: String
    private var isConnected = false
    private val sender = ProfileData.user_email
    val user_type = ProfileData.user_type
    var chatRoom = ChatRoom()
    var chatData = ChatData()
    var transData = TransData()
    var chatList = arrayListOf<ChatData>()
    val chatAdapter = ChatAdapter(chatList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //HomeFragment에서 intent로 값 받아오기
        receiver = intent.getStringExtra("receiver") ?: ""
        tourist_info = intent.getStringExtra("tourist_info")?: ""
        tourist_name = intent.getStringExtra("tourist_name")?: ""
        guide_name = intent.getStringExtra("guide_name")?: ""
        room_id = intent.getStringExtra("room_id").toString()

        //room_id가 null일때 새로 생성
        if (room_id == "null") {
            room_id = ProfileData.user_email + receiver
        }
        Log.d("Room_id", room_id)

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
        chatList()

        //소켓
        ChatManager.instance.setChatListener(chatListener)
        ChatManager.instance.init(sender, receiver, room_id)


        //리스너
        chat_send_iv.setOnClickListener {
            ChatManager.instance.userCount()
            setChatData()
            translation_and_send()

        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun setChatData() {
        var receiver_type = ""
        var targetlang = ""
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm")

        if (user_type.equals("tourist")) { //sender타입에 따라 receiver타입 변경
            receiver_type = "guide"
            targetlang = "ko"
        } else {
            receiver_type = "tourist"
            targetlang = tourist_info
        }

        chatData
        chatData.source = LocaleHelper.getLanguage(this)
        chatData.source_text = chat_input_et.text.toString()
        chatData.target = targetlang
        chatData.target_text = ""
        chatData.sender = sender
        chatData.receiver = receiver
        chatData.sender_type = user_type
        chatData.receiver_type = receiver_type
        chatData.msg_dt = df.format(Date(System.currentTimeMillis()))
        chatData.room_id = room_id
        chatData.room_member_cnt = "2"
    }

    fun sendMessage() {
        if (chatData.source_text != "") { //채팅입력창에 빈칸입력안돼게 if문 처리
            chatAdapter.addChat(chatData)
            chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
            chat_input_et.setText("")
            ChatManager.instance.sendMessage(chatData)
            Log.d("SendMessage", chatData.toString())
        } else {
            null
        }
    }

    fun chatList() {
        chatDAO.chat_list(room_id, callback = object : Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body: ArrayList<ChatHistory>? = result?.body

                try {

                    chatAdapter.setChatList(chatList)
                    for (i:Int in 0 until body!!.size) {
                        var chat: ChatHistory = body[i]
//                        Log.d("JsonObject", chat.toString())

                        chatData = ChatData()
                        chatData.source = chat.source_lang
                        chatData.source_text = chat.source_text
                        chatData.target = chat.target_lang
                        chatData.target_text = chat.target_text
                        chatData.sender = chat.sender_id
                        chatData.receiver = chat.receiver_id
                        chatData.sender_type = chat.sender_user_type
                        chatData.receiver_type = chat.receiver_user_type
                        chatData.msg_dt = chat.msg_dt
                        chatList.add(chatData)
                        println(chatData)
                    }

                    chatAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.d("ChatList", "성공")
            }

            override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })
    }

    fun translation_and_send() {
        chatDAO.translation(chatData, callback = object : Callback<PapagoResonse> {
            override fun onResponse(call: Call<PapagoResonse>, response: Response<PapagoResonse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body = result?.body
                Log.d("translation Body", body.toString())

                if (body != null) {
                    chatData.target_text = body.target_text
                    sendMessage()
                }
                Log.d("translation", "성공")
            }

            override fun onFailure(call: Call<PapagoResonse>, t: Throwable) {
                Log.d("translation", "실패")
            }
        })
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
