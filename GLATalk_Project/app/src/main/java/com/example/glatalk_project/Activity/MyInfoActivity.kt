package com.example.glatalk_project.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.Model.UserVO
import com.example.glatalk_project.R
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.BaseResponse
import kotlinx.android.synthetic.main.activity_my_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)
        var userDao = UserDAO
        var userVo = UserVO()
        //common title 수정버튼 누르면 넘어감


        //기존 서버에서 받아와서 editText에 표시


        //비밀번호 변경
//        my_info_pwd_change_btn.setOnClickListener{
//            Pwd_Change()
//        }
        Log.d("result", "${TokenData.loginToken}")

        userDao.userDetail(callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    var result = response.body()!!.toString()
                    Log.d("result", "$result")
                }

            }
        })

        Log.d("result", "ddddddd")

<<<<<<< Updated upstream
        my_info_name_et.setText(UserD)
=======
        my_info_name_et.setText(userVo.user_name)
        my_info_phone_et.setText(userVo.phone_number)
        my_info_email_et.setText(userVo.user_email)
        my_info_country_select_tv.setText(userVo.country_cd)


>>>>>>> Stashed changes
    }


//    private fun Pwd_Change(){
//        val intent = Intent(this, PwdChangeActivity::class.java)
//        startActivity(intent)
//    }
}