package com.example.glatalk_project.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.R
import com.example.glatalk_project.core.data.ChatRoom
import com.example.glatalk_project.core.util.RoomVIewHolder

class ChatRoomListAdapter(private val itemList: List<ChatRoom>): RecyclerView.Adapter<RoomVIewHolder>() {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomVIewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.guide_room_custom, parent, false)
        return RoomVIewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RoomVIewHolder, position: Int) {
        val item = itemList[position]

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
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}