package com.example.glatalk_project.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Activity.ChatActivity
import com.example.glatalk_project.Model.HomeDAO
import com.example.glatalk_project.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.ChatRoomListAdapter
import com.example.glatalk_project.core.data.ChatRoom
import com.example.glatalk_project.network.BaseResponse
//import com.example.glatalk_project.core.data.ChatRoom
import kotlinx.android.synthetic.main.fragment_home_guide.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment: Fragment(){
    val roomList = arrayListOf<ChatRoom>()
    val profileData = ProfileData()
    //암시 테스트용
//    val roomList: MutableList<ChatRoom> = mutableListOf(
//            ChatRoom("횽근이", "한국", "rkskedk",true,"0","이형근","한라산","2020-05-08(금) 12:30",false,),
//            ChatRoom("횽근이", "한국", "rkskedk",true,"0","이형근","한라산","2020-05-08(금) 12:30",false,),
//            ChatRoom("횽근이", "한국", "rkskedk",true,"0","이형근","한라산","2020-05-08(금) 12:30",false,),
//            ChatRoom("횽근이", "한국", "rkskedk",true,"0","이형근","한라산","2020-05-08(금) 12:30",false,)
//    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_guide, container, false)

        //타이틀,통신 분기점
        if(profileData.user_type.equals("guide")) {
            view.home_guide_tv.text = "관광객 대화 정보"
            guideHomeNetWorking()
        } else{
            view.home_guide_tv.text = "대화 가능 가이드 정보"
            touristHomeNetWorking()
        }

        //리사이클러뷰
        val adapter = ChatRoomListAdapter(roomList)
        view.home_guide_rv.adapter = adapter

        //리사이클러뷰 클릭리스너
        adapter.setItemClickListener(object: ChatRoomListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                goToChat()
                adapter.notifyDataSetChanged()
            }
        })

        //리사이클러뷰 롱클릭리스너
        adapter.setItemLongClickListener(object: ChatRoomListAdapter.OnItemLongClickListener {
            override fun onLongClick(v: View, position: Int): Boolean {
                val result = roomList.remove(roomList[position])
                adapter.notifyDataSetChanged()
                return result
            }
        })

        return  view
    }

    private fun goToChat(){
        val intent = Intent(getActivity(), ChatActivity::class.java)
        startActivity(intent)
    }

    private fun touristHomeNetWorking(){
        HomeDAO.tourist_home(callback = object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("Tour_Home", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Tour_Home", "실패")
            }
        })
    }

    private fun guideHomeNetWorking(){
        HomeDAO.guide_home(callback = object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("Guide_Home", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Guide_Home", "실패")
            }
        })
    }
}