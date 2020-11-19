package com.example.glatalk_project.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Activity.ChatActivity
import com.example.glatalk_project.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatRoomListAdapter
import com.example.glatalk_project.core.data.ChatRoom
//import com.example.glatalk_project.core.data.ChatRoom
import kotlinx.android.synthetic.main.fragment_home_guide.view.*


class HomeFragment: Fragment(){
    val roomList = arrayListOf<ChatRoom>()
    val profileData = ProfileData()
    //암시 테스트용
//    val chatroomList: MutableList<ChatRoom> = mutableListOf(
//            ChatRoom("중국어", "2020-05-08(금) 16:40", "rkskedk"),
//            ChatRoom("일본어", "2020-05-08(금) 12:30","qnjopbnokavl"),
//            ChatRoom("영어", "2020-05-08(금) 11:10","qwjrbopa;sndkl"),
//            ChatRoom("중국어", "2020-05-08(금) 16:40","qhrgovl"),
//            ChatRoom("일본어", "2020-05-08(금) 12:30","qhiwov"),
//            ChatRoom("영어", "2020-05-08(금) 11:10","riejejd"),
//            ChatRoom("중국어", "2020-05-08(금) 16:40","WWWWWWW"),
//            ChatRoom("일본어", "2020-05-08(금) 12:30","gidkrkfkekdk"),
//            ChatRoom("영어", "2020-05-08(금) 11:10","이형근")
//    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_guide, container, false)


        val adapter = ChatRoomListAdapter(roomList)
        adapter.setItemClickListener(object: ChatRoomListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                goToChat()
                adapter.notifyDataSetChanged()
            }
        })

        adapter.setItemLongClickListener(object: ChatRoomListAdapter.OnItemLongClickListener {
            override fun onLongClick(v: View, position: Int): Boolean {
                val result = roomList.remove(roomList[position])
                adapter.notifyDataSetChanged()

                return result
            }
        })

        view.home_guide_rv.adapter = adapter
        //이후 코드 구현

        if(profileData.user_type.equals("guide")) {
            view.home_guide_tv.text = "관광객 대화 정보"
        }
        if(profileData.user_type.equals("tourist")){
            view.home_guide_tv.text = "대화 가능 가이드 정보"
        }

        return  view
    }

    private fun goToChat(){
        val intent = Intent(getActivity(), ChatActivity::class.java)
        startActivity(intent)
    }
}