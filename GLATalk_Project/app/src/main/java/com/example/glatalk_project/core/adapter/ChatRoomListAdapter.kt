package com.example.glatalk_project.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.R
import com.example.glatalk_project.core.data.ChatRoom
import kotlinx.android.synthetic.main.ui_room_custom.view.*

class ChatRoomListAdapter(private val itemList: MutableList<ChatRoom>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.ui_room_custom, parent, false)
        return RoomVIewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        val holder = RoomVIewHolder(holder.itemView)
        holder.onBind(item)
        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
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

    inner class RoomVIewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var view = v
        fun onBind(item: ChatRoom) {
            view.room_lang_tv.text = item.language
            view.room_time_tv.text = item.time
            view.room_name_tv.text = item.name
        }
    }
}