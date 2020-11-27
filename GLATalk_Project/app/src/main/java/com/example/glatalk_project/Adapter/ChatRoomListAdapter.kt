package com.example.glatalk_project.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.ChatRoom
//import com.example.glatalk_project.Data.ChatRoom
import kotlinx.android.synthetic.main.ui_room_custom.view.*
import com.example.glatalk_project.Data.ProfileData

class ChatRoomListAdapter(private val roomList: MutableList<ChatRoom>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val GUIDE_ROOM = 0
    private val TOUR_ROOM = 1
//    var chatData = ChatData()

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ui_room_custom, parent, false)
        return when (viewType) {
            GUIDE_ROOM -> {
                GuideRoomViewHolder(view)
            }
            else -> {
                TourRoomViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (ProfileData.user_type.equals("guide")) {
            return GUIDE_ROOM
        } else {
            return TOUR_ROOM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is GuideRoomViewHolder -> {
                holder.onBind(roomList[position])
                holder.itemView.setOnLongClickListener {
                    itemLongClickListener.onLongClick(it, position)
                }
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(it, position)
                }
                //bind visible 추가해야됨
//                holder.dateVisible(position)
            }
            is TourRoomViewHolder -> {
                holder.onBind(roomList[position])
                holder.itemView.setOnLongClickListener {
                    itemLongClickListener.onLongClick(it, position)
                }
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(it, position)
                }
                //bind visible 추가해야됨
//                holder.dateVisible(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onLongClick(v: View, position: Int): Boolean
    }

    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var itemLongClickListener: OnItemLongClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }

    inner class GuideRoomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view = v
        fun onBind(item: ChatRoom) {
            view.room_lang_title_tv.text = "관광객 언어"
            view.room_lang_tv.text = item.tourist_info

            view.room_time_title_tv.text = context.getString(R.string.last_time)
            view.room_time_tv.text = item.last_chat_time
            if(item.last_chat_time == "null"){
                view.room_time_ll.visibility = View.GONE
            } else {
                view.room_time_ll.visibility = View.VISIBLE
            }

            view.room_name_tv.text = item.tourist_name

            view.room_state_tv.text = context.getString(R.string.new_message)
            if(item.new_msg == true){
                view.room_state_tv.visibility = View.VISIBLE
            } else {
                view.room_state_tv.visibility = View.GONE
            }
        }
    }

    inner class TourRoomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view = v
        fun onBind(item: ChatRoom) {
            view.room_lang_title_tv.text = context.getString(R.string.guide_info)
            view.room_lang_tv.text = item.guide_info

            view.room_time_title_tv.text = context.getString(R.string.last_time)
            view.room_time_tv.text = item.last_chat_time
            if(item.last_chat_time == "null"){
                view.room_time_title_tv.visibility = View.GONE
                view.room_time_tv.visibility = View.GONE
            } else {
                view.room_time_title_tv.visibility = View.VISIBLE
                view.room_time_tv.visibility = View.VISIBLE
            }

            view.room_name_tv.text = item.guide_name

            view.room_able_ll.visibility = View.VISIBLE
            view.room_able_title_tv.text = context.getString(R.string.enable_time)
            view.room_able_tv.text = item.guide_time

            view.room_state_tv.text = context.getString(R.string.chatting)
            if(item.chat_yn == "1"){
                view.room_state_tv.visibility = View.VISIBLE
            } else {
                view.room_state_tv.visibility = View.GONE
            }
        }
    }
}