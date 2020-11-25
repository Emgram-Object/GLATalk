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
import com.example.glatalk_project.Data.ChatRoom
import com.example.glatalk_project.Data.TransData
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.util.ChatManager
import com.example.glatalk_project.network.data.response.BaseResponse
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChatActivity : AppCompatActivity() {
    //변수 선언분
    private var chatDAO = ChatDAO
    private var receiver = ""
    private var tourist_info = ""
    private lateinit var room_id: String
    private var sender_id = ProfileData.user_name
    var chatList = arrayListOf<ChatData>()
    val chatAdapter = ChatAdapter(chatList)
    var chatData = ChatData()
    private var isConnected = false
    var chatRoom = ChatRoom()
    val sender = ProfileData.user_name
    val user_type = ProfileData.user_type
    var transData = TransData()
    var rcv_type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //HomeFragment에서 intent로 값 받아오기
        receiver = intent.getStringExtra("receiver_id") ?: ""
        tourist_info = intent.getStringExtra("tourist_info") ?: ""
        room_id = intent.getStringExtra("room_id") ?: ""
//        var room_id = ProfileData.user_name+receiver //테스트용
        if (room_id == "null") { //room_id가 null일때 새로 생성
            room_id = ProfileData.user_email + receiver
        }
        Log.d("Room_id", room_id)

        //채팅내역 리사이클러뷰
        chat_rv.adapter = chatAdapter

        //ㅂ유저타입 분기에 따라 보여주는 뷰 정보 처리
        if (ProfileData.user_type.equals("guide")) {
            title_tv.text = chatRoom.tourist_name
            mobil_info.visibility = View.VISIBLE
            mobil_info.text = chatRoom.tourist_info
        } else {
            title_tv.text = chatRoom.guide_name
            mobil_info.visibility = View.GONE
        }


        //채팅내역 레트로핏 통신
        chatList(room_id)

        //소켓
        ChatManager.instance.setChatListener(chatListener)
        ChatManager.instance.init(sender, receiver, room_id)

        //리스너
        chat_send_iv.setOnClickListener {
            setChatData()
            translation_and_send()
        }

        back_btn.setOnClickListener {
            finish()
        }
    }

    fun setChatData(){
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

        chatData = ChatData(
                LocaleHelper.getLanguage(this),
                chat_input_et.text.toString(),
                targetlang,
               "",
                sender,
                receiver,
                user_type,
                receiver_type,
                df.format(Date(System.currentTimeMillis())),
                room_id,
                "")

        Log.d("SetChatData", chatData.toString())
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

    fun chatList(room_id: String) {
        chatDAO.chat_list(room_id, callback = object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body = result?.body.toString()

                val regex = "[^=,{}\\[\\] ]{1,}=[^=,{}\\[\\] ]{0,}".toRegex()
                try {
                    body = regex.replace(body) {
                        var text = it.value.substring(it.value.indexOf('=') + 1)
                        if (text.equals("null")) {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":null"
                        } else {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":\"$text\""
                        }
                    }
                    var jsonArray: JSONArray = JSONArray(body)

                    for (i in 0..jsonArray.length()) {
                        val chat: JSONObject = jsonArray.getJSONObject(i)

                        chatData = ChatData()
                        chatData.source = chat["source_lang"] as String
                        chatData.source_text = chat["source_text"] as String
                        chatData.target = chat["target_lang"] as String
                        chatData.target_text = chat["target_text"] as String
                        chatData.sender = chat["sender_id"] as String
                        chatData.receiver = chat["receiver_id"] as String
                        chatData.sender_type = chat["sender_user_type"] as String
                        chatData.receiver_type = chat["receiver_user_type"] as String
                        chatData.msg_dt = chat["msg_dt"] as String
                        chatAdapter.setChatList(chatList)
                        chatList.add(chatData)
                    }
                    chatAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.d("ChatList", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })
    }

    fun translation_and_send() {
        chatDAO.translation(chatData, callback = object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()
                var resultCode = result?.resultCode
                var desc = result?.desc
                var body = result?.body.toString()
                Log.d("translation Body", body)

                try {
                    var jsonObject = JSONObject(body)
                    Log.d("body", body)

                    transData.source = jsonObject["source"] as String
                    transData.target = jsonObject["target"] as String
                    transData.target_text = jsonObject["target_text"] as String
                    chatData.target_text = jsonObject["target_text"] as String
                    //send

                    sendMessage()
                    Log.d("TransData", transData as String)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.d("translation", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
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
