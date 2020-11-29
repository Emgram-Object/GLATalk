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
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.ChatRoomListAdapter
import com.example.glatalk_project.Data.ChatRoom
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Model.ChatDAO
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.response.HomeResponse
import kotlinx.android.synthetic.main.fragment_home_guide.view.*
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    val roomList = arrayListOf<ChatRoom>()
    lateinit var chatRoom: ChatRoom
    lateinit var adapter: ChatRoomListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_guide, container, false)
//        Log.d("home_token", "${TokenData.loginToken}")
//        Log.d("home_type", ProfileData.user_type)

        //타이틀,통신 분기점
        if (ProfileData.user_type == "guide") {
            view.home_guide_tv.text ="관광객 대화정보"
            guideHomeNetWorking()
        } else if (ProfileData.user_type == "tourist") {
            view.home_guide_tv.text = getString(R.string.enable_guide_info)
            touristHomeNetWorking()
        }

        //리사이클러뷰
        adapter = ChatRoomListAdapter(roomList, context!!)
        view.home_guide_rv.adapter = adapter

        //리사이클러뷰 클릭리스너
        adapter.setItemClickListener(object : ChatRoomListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                goToChat(position)
                adapter.notifyDataSetChanged()
            }
        })

        //리사이클러뷰 롱클릭리스너
        adapter.setItemLongClickListener(object : ChatRoomListAdapter.OnItemLongClickListener {
            override fun onLongClick(v: View, position: Int): Boolean {
//                val result = roomList.remove(roomList[position])
//                adapter.notifyDataSetChanged()
                ChatDAO.ChatDelete(roomList[position].room_id) //채팅내역삭제 api 호출(팝업띄워서 ok눌렀을때만 삭제되게 해주세용!)
                return true
            }
        })
        return view
    }

    private fun goToChat(position: Int) {
        val intent = Intent(getActivity(), ChatActivity::class.java)
        intent.putExtra("room_id", roomList[position].room_id)
        if(ProfileData.user_type.equals("tourist")) {
            intent.putExtra("receiver", roomList[position].guide_id)
            intent.putExtra("guide_name", roomList[position].guide_name)
        } else{
            intent.putExtra("receiver", roomList[position].tourist_id)//tourist_id로 수정할예정
            intent.putExtra("tourist_info", roomList[position].tourist_info)
            intent.putExtra("tourist_namer", roomList[position].tourist_name)
        }
        startActivity(intent)
    }

    private fun touristHomeNetWorking() {
        HomeDAO.tourist_home(callback = object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                if (response.isSuccessful) {
                    var result = response.body()!!
                    var resultCode = result.resultCode
                    var desc = result.desc
                    var body = result.body

                    try {
                        for (i:ChatRoom in body!!) {
                            roomList.add(i)
                            println(roomList.toString())
                        }
                        adapter.notifyDataSetChanged()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
//                    Log.d("Tour_Home", "성공" + resultCode + desc)
                }
            }
            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
//                Log.d("Tour_Home", "실패")
            }
        })
    }

    private fun guideHomeNetWorking() {
        HomeDAO.guide_home(callback = object : Callback<HomeResponse> {
            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body

                try {
                    for (i:ChatRoom in body!!) {
                        roomList.add(i)
                    }
                    adapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
//                Log.d("Tour_Home", "성공" + roomList)
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
//                Log.d("Guide_Home", "실패")
            }
        })
    }
}