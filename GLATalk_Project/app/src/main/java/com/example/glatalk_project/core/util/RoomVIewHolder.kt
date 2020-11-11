package com.example.glatalk_project.core.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.core.data.ChatRoom
import kotlinx.android.synthetic.main.ui_guide_room_custom.view.*

class RoomVIewHolder(v: View): RecyclerView.ViewHolder(v) {
    var view = v

    fun bind(item: ChatRoom){
        view.tour_lang_tv.text = item.language
        view.last_time_tv.text = item.time
        view.tour_name_tv.text = item.name
    }
}