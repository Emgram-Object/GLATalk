package com.example.glatalk_project.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.R
import com.example.glatalk_project.core.data.ChatRoom
import com.example.glatalk_project.core.util.RoomVIewHolder

class ChatRoomListAdapter(private val itemList: MutableList<ChatRoom>): RecyclerView.Adapter<RoomVIewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomVIewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.ui_guide_room_custom, parent, false)
        return RoomVIewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RoomVIewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply {
            bind(item)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    interface OnItemLongClickListener {
        fun onLongClick(v: View, position: Int) : Boolean
    }
    private lateinit var itemClickListener : OnItemClickListener
    private lateinit var itemLongClickListener: OnItemLongClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
    fun setItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener
    }
}