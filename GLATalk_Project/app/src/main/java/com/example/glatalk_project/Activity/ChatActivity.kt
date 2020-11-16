package com.example.glatalk_project.Activity//package com.example.glatalk_project.Activity
//
//import android.service.autofill.UserData
//import androidx.appcompat.app.AppCompatActivity

//import com.example.glatalk_project.Model.*
////import com.example.glatalk_project.Model.ChatData
//import com.example.glatalk_project.R
//import com.example.glatalk_project.core.adapter.ChatAdapter
////import com.example.glatalk_project.core.adapter.ChatAdapter

//import kotlinx.android.synthetic.main.activity_chat.*
//import java.text.SimpleDateFormat
//import java.util.*
//
//class ChatActivity:AppCompatActivity(){
//     val layoutResourceId:Int
//        get()= R.layout.activity_chat
//     //var viewModel: ChatViewModel
//     private lateinit var chatAdapter: ChatAdapter
//
//    lateinit var Model: ChatModel
//    private var UserVO = UserVO()
//
//    private var roomName=""
//    private var receiver_id = ""
//    private var target = ""
//    private var user_type=""
//   // private var : GuideData? = null
//
//    private var isConnected = false
//    private var chatData: ChatData? = null
//
//    fun initView(){
//        roomName = intent.getStringExtra("reserve_id")?:""
//        receiver_id = intent.getStringExtra("receiver_id")?:""

//        user_type = toStringGson(intent.getStringExtra("user_type")) //?????모르갯음

//
//        if(roomName.isEmpty()||receiver_id.isEmpty()){
//            finish()
//        }
//
//        chatAdapter = ChatAdapter()
//        chat_rv.adapter = chatAdapter
//

//     //   Model.chatList(roomName)
//
//        //viewModel.chatList(roomname)
//
//       // ChatManager.instace.init(UserVO.user_name?: "", receiver_id, roomName)

//        Model.chatList(roomName)
//
//        //viewModel.chatList(roomname)
//
//        ChatManager.instace.init(UserVO.user_name?: "", receiver_id, roomName)

//
//        fun initListener(){
//            chat_send_iv.setOnClickListener{
//                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                val chatData = ChatData()
//                chatData.source_lang = UserVO.country_cd //userinfomanager = 유저 정보 담고 있는 거
//                chatData.target_lang = ""//받아오기
//                chatData.source_text = chat_input_et.text.toString()
//                chatData.sender_id = UserVO.user_email
//                chatData.receiver_id = receiver_id
//                chatData.sender_user_type = ""
//                chatData.receiver_user_type = ""
//                chatData.room_id = roomName
//                chatData.msg_dt = df.format(Date(System.currentTimeMillis()))
//                this.chatData = chatData
//
//                //viewModel.translation(chatData)
//                runOnUiThread{
//                    chatAdapter.addChat(chatData)
//
//                    chat_input_et.setText("")
//                    chat_rv.scrollToPosition(chatAdapter.getChatSize()-1)
//                }
//            }
//        }
////        ChatManager.instance.setChatListener(chatListener)
//
//
//    }

//    private var chatListener = object: BaseChat.OnChatListener {
//        override fun onConnected() {
//            isConnected = true
//        }
//
//        override fun onReceive(chat: ChatData) {
//            runOnUiThread {
//                //chat.type = C.MessageType.CHAT_OTHER.index
//                chatAdapter.addChat(chat)
//                chat_rv.scrollToPosition(chatAdapter.getChatSize() - 1)
//            }
//        }
//
//        override fun onConnectError() {
//            isConnected = false
//        }
//
//        override fun onDisconnect() {
//            isConnected = false
//        }
//    }
//
//    override fun onDestroy() {
//        //keyboardManager.detachKeyboardListeners()
//        //ChatManager.instance.release()
//        super.onDestroy()
//    }
//

//
//}
////
////class ChatViewModel(private val model: ChatModel){
////
////}
////livedata를 써야 하나??
//
