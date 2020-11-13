package com.example.glatalk_project.core.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.core.data.ChatRoom
import kotlinx.android.synthetic.main.ui_room_custom.view.*

class RoomVIewHolder(v: View): RecyclerView.ViewHolder(v) {
    var view = v

    fun bind(item: ChatRoom){
        view.room_lang_tv.text = item.language
        view.room_time_tv.text = item.time
        view.room_name_tv.text = item.name
    }
}