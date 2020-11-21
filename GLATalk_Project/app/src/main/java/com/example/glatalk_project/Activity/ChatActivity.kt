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

    private var chatDAO = ChatDAO
    private var room_id = ""
    private var receiver_id = ""
    private var sender_id = ProfileData.user_name
    var chatList = arrayListOf<ChatData>()
    val chatAdapter = ChatAdapter(chatList)
    var chatData = ChatData()
    private var isConnected = false
    var chatRoom = ChatRoom()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

       room_id = intent.getStringExtra("room_id") ?: ""
        receiver_id = intent.getStringExtra("receiver_id") ?: ""

        chat_rv.adapter = chatAdapter

        if(ProfileData.user_type.equals("guide")){
            title_tv.text = chatRoom.tourist_name
            mobil_info.visibility = View.VISIBLE
            mobil_info.text = chatRoom.tourist_info
        }else{
            title_tv.text = chatRoom.guide_name
            mobil_info.visibility = View.GONE
        }

        chatDAO.chat_list(room_id, callback = object: Callback<BaseResponse>{
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

                    for(i in 0..jsonArray.length()){
                        val chat: JSONObject = jsonArray.getJSONObject(i)

                        chatData = ChatData()
                        chatData.source_lang = chat["source_lang"] as String
                        chatData.source_text = chat["source_text"] as String
                        chatData.target_lang = chat["target_lang"] as String
                        chatData.target_text = chat["target_text"] as String
                        chatData.sender_id = chat["sender_id"] as String
                        chatData.receiver_id = chat["receiver_id"] as String
                        chatData.sender_user_type = chat["sender_user_type"] as String
                        chatData.receiver_user_type = chat["receiver_user_type"] as String
                        chatData.msg_dt = chat["msg_dt"] as String
                        chatAdapter.setChatList(chatList)
                        chatList.add(chatData)
                    }
                    chatAdapter.notifyDataSetChanged()


                } catch (e: JSONException){
                    e.printStackTrace()
                }

                Log.d("ChatList", "성공"+resultCode+desc)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("ChatList", "실패")
            }
        })

        chatDAO.translation(chatData, callback = object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body.toString()


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
                sender_id,
                receiver_id,
                "tourist",
                "guide",
                df.format(Date(System.currentTimeMillis())),
                "")
        println(chatData.sender_id+", "+chatData.receiver_id)
        if(chatData.source_text != "") {
//            chatadapter.addItem(chatData)
            chatAdapter.addChat(chatData)
            chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
            chat_input_et.setText("")
            ChatManager.instance.sendMessage(chatData)
        }
    }


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