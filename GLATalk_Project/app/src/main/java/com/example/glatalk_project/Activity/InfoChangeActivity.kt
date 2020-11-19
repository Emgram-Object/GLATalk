package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.ProfileData
import com.example.glatalk_project.R
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.ProfileRequest
import kotlinx.android.synthetic.main.activity_my_info.*
import kotlinx.android.synthetic.main.activity_my_info_change.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InfoChangeActivity : AppCompatActivity() {

    var input_user_name: String = ""
    var input_phone_num: String = ""
    var input_user_EngName: String = ""
    var input_user_country: String ?= ""

    var myDao = MyDao
    lateinit var input: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info_change)

        setTexts()

        modify_ok_btn.setOnClickListener {
            changeMyInfo()
            Thread.sleep(100)
            gotoMy()
        }

        if (input_user_name.isBlank() || input_phone_num.isBlank() || input_user_EngName.isBlank()) {
            modify_ok_btn.setBackgroundResource(R.drawable.rounded_square_dim)
        }
        //common title의 뒤로가기 누르면 SaveCheck() 호출하기


    }

    private fun setTexts() {
        my_info_chg_name_et.setText(ProfileData.user_name)
        my_info_chg_phone_et.setText(ProfileData.phone_number)
//user english name은 뭘까?
     //   my_info_chg_country_select_tv.text = ProfileData.country_cd
    }

    private fun getTexts() {
        input_user_name = my_info_chg_name_et.text.toString()
        input_phone_num = my_info_chg_phone_et.text.toString()
  //      input_user_country = my_info_country_select_tv.text.toString() //스피너로 바꾸면 에러 해결될 듯
    }

    private fun changeMyInfo() {
        getTexts()
        myDao.modify_info(profileRequest = ProfileRequest(input_user_name as RequestBody, input_phone_num as RequestBody, input_user_country as RequestBody), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("tag", "success")
            }
        }
        )
    }

    private fun gotoMy() {
        val intent = Intent(this, MyInfoActivity::class.java) //마이메뉴 화면으로 넘어가기
        startActivity(intent)
        finish()
    }


    private fun SaveCheck() {
        //팝업창 띄우기
    }
}