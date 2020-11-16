package com.example.glatalk_project.core.adapter

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.R
import com.example.glatalk_project.core.data.ChatModel
import kotlinx.android.synthetic.main.ui_my_chat.view.*
import kotlinx.android.synthetic.main.ui_other_chat.view.*
import java.lang.Exception
import java.text.SimpleDateFormat


class ChatAdapter(val chatList: ArrayList<ChatModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val CHAT_MINE = 0
    private val CHAT_OTHER = 1
//    private val chatList = ArrayList<ChatData>()


    fun addChat(chat: ChatModel){
        chatList.add(chat)
        notifyItemInserted(chatList.size)
    }

    fun addItem(item: ChatModel) {//아이템 추가
        if (chatList != null) {
            chatList.add(item)
        }
    }

    fun setChatList(chatList: ArrayList<ChatModel>){
        chatList?.let{
            this.chatList.clear()
            this.chatList.addAll(chatList)
            notifyDataSetChanged()
        }
    }

    fun getChatSize(): Int{
        return chatList.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            CHAT_MINE -> {
                val binding =
                        LayoutInflater.from(parent.context).inflate(R.layout.ui_my_chat, parent, false)
                ChatMineViewHolder(binding)
            }
            else -> {
                val binding =
                        LayoutInflater.from(parent.context).inflate(R.layout.ui_my_chat,
                        parent,
                        false)

                ChatOtherViewHolder(binding)
            }
        }
    }


    override fun getItemCount(): Int =chatList.size

    override fun getItemViewType(position: Int): Int {
        if(chatList[position].sender_user_type.equals("tourist")){
            return CHAT_MINE
        }else{
            return CHAT_OTHER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ChatMineViewHolder -> {
                holder.onBind(chatList[position])
                //bind visible 추가해야됨
                holder.dateVisible(position)
            }
            is ChatOtherViewHolder -> {
                holder.onBind(chatList[position])
                //bind visible 추가해야됨
                holder.dateVisible(position)
            }
        }
    }

//날짜 받아오는 거 어떤 형식인지 확인해보기
inner class ChatMineViewHolder(v: View): RecyclerView.ViewHolder(v) {
    var view = v
    fun onBind(chat: ChatModel) {
        view.chat_date.text = chat.msg_dt
        view.message_mine_tv.text = chat.source_text
        view.message_tran_tv.text = chat.target_text
        view.chat_time.text = chat.msg_dt
    }

    fun dateVisible(position: Int) {
        if(position == 0) {
            view.chat_time.visibility = View.VISIBLE
        } else {
            val prev = dateParser(chatList[position-1].msg_dt)
            val current = dateParser(chatList[position].msg_dt)
            if(prev.equals(current)) {
                view.chat_time.visibility = View.GONE
            } else {
                view.chat_time.visibility = View.VISIBLE
            }
        }
    }

    private fun dateParser(dt: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sdf2 = SimpleDateFormat("yyyy.MM.dd (E)")
        try {
            val date = sdf.parse(dt)
            val dt2 = sdf2.format(date)

            return dt2
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }
}

    inner class ChatOtherViewHolder(v:View): RecyclerView.ViewHolder(v) {
        var view = v
        fun onBind(chat: ChatModel) {
            view.chat_other_date.text = chat.msg_dt
            view.message_other_tv.text = chat.source_text
            view.message.text = chat.target_text
            view.other_chat_time.text = chat.msg_dt
        }

        fun dateVisible(position: Int) {
            if(position == 0) {
                view.chat_time.visibility = View.VISIBLE
            } else {
                val prev = dateParser(chatList[position-1].msg_dt)
                val current = dateParser(chatList[position].msg_dt)
                if(prev.equals(current)) {
                    view.chat_time.visibility = View.GONE
                } else {
                   view.chat_time.visibility = View.VISIBLE
                }
            }
        }

        private fun dateParser(dt: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val sdf2 = SimpleDateFormat("yyyy.MM.dd (E)")
            try {
                val date = sdf.parse(dt)
                val dt2 = sdf2.format(date)

                return dt2
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }
    }

}