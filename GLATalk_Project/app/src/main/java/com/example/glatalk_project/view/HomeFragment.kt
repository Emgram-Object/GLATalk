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
import com.example.glatalk_project.network.data.response.BaseResponse
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

        //타이틀,통신 분기점
        if (ProfileData.user_type.equals("guide")) {
            view.home_guide_tv.text = "관광객 대화 정보"
            guideHomeNetWorking()
        } else if (ProfileData.user_type.equals("tourist")) {
            view.home_guide_tv.text = "대화 가능 가이드 정보"
            touristHomeNetWorking()
        }

        //리사이클러뷰
        adapter = ChatRoomListAdapter(roomList)
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
                val result = roomList.remove(roomList[position])
                adapter.notifyDataSetChanged()
                return result
            }
        })
        return view
    }

    private fun goToChat(position: Int) {
        val intent = Intent(getActivity(), ChatActivity::class.java)
        intent.putExtra("room_id", roomList[position].room_id)
        startActivity(intent)
    }

    private fun touristHomeNetWorking() {
        HomeDAO.tourist_home(callback = object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body.toString()

                val regex = "[^=,{}\\[\\] ]{1,}=[^=,{}\\[\\] ]{0,}".toRegex()
                try {
                    body = regex.replace(body) {
                        var text = it.value.substring(it.value.indexOf('=') + 1)
                        if (text.equals("null")) {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":null"
                        } else {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":\"$text\""
                        }
                    }
                    var jsonArray: JSONArray = JSONArray(body)
                    for (i in 0 until jsonArray.length()) {
                        val room = jsonArray.getJSONObject(i)

                        chatRoom = ChatRoom()
                        chatRoom.guide_name = room["guide_name"] as String
                        chatRoom.guide_info = room["guide_info"] as String
                        chatRoom.guide_time = room["guide_time"] as String
                        chatRoom.last_chat_time = room.getString("last_chat_time")
                        chatRoom.chat_yn = room["chat_yn"] == null ?: false
                        chatRoom.room_id = room.getString("room_id")
                        roomList.add(chatRoom)
                    }
                    adapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.d("Tour_Home", "성공" + resultCode + desc)
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Tour_Home", "실패")
            }
        })
    }

    private fun guideHomeNetWorking() {
        HomeDAO.guide_home(callback = object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                var resultCode = result.resultCode
                var desc = result.desc
                var body = result.body.toString()

                val regex = "[^=,{}\\[\\] ]{1,}=[^=,{}\\[\\] ]{0,}".toRegex()
                try {
                    body = regex.replace(body) {
                        var text = it.value.substring(it.value.indexOf('=') + 1)
                        if (text.equals("null")) {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":null"
                        } else {
                            "\"" + it.value.substring(0, it.value.indexOf('=')) + "\":\"$text\""
                        }
                    }


                    var jsonArray: JSONArray = JSONArray(body)
                    for (i in 0 until jsonArray.length()) {
                        val room = jsonArray.getJSONObject(i)

                        chatRoom.tourist_name = room["tourist_name"] as String
                        chatRoom.tourist_info = room["tourist_info"] as String
                        chatRoom.last_chat_time = room["last_chat_time"] as String
                        chatRoom.new_msg = room["new_msg"] as Boolean
                        chatRoom.room_id = room["room_id"] as String
                        roomList.add(chatRoom)
                    }
                } catch (e: JSONException) {
                    null
                }
                adapter.notifyDataSetChanged()
                Log.d("Guide_Home", "성공")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Guide_Home", "실패")
            }
        })
    }
}