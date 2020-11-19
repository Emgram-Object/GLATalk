package com.example.glatalk_project.Activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_my_info.*
import kotlinx.android.synthetic.main.activity_my_info_change.*

class InfoChangeActivity:AppCompatActivity(){

    var input_user_name:String = ""
    var input_phone_num:String=""
    var input_user_EngName:String =""
    lateinit var input: EditText
    var profileData = ProfileData()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info_change)

        common_title_info_change.setTitle("내 정보 변경")

        setTexts()


        modify_ok_btn.setOnClickListener{
            //val intent = Intent(this, ) //마이메뉴 화면으로 넘어가기
            startActivity(intent)
        }

        if(input_user_name.isBlank()||input_phone_num.isBlank()||input_user_EngName.isBlank()){
            modify_ok_btn.setBackgroundResource(R.drawable.rounded_square_dim)
        }
        //common title의 뒤로가기 누르면 SaveCheck() 호출하기

        modify_ok_btn.setOnClickListener(){
            input_user_name = my_info_name_et.text.toString()
            input_phone_num = my_info_phone_et.text.toString()
            input_user_EngName = my_info_chg_english_name_et.text.toString()
        }

    }
    private fun setTexts(){
        my_info_chg_name_et.setText(profileData.user_name)
        my_info_chg_phone_et.setText(profileData.phone_number)
//user english name은 뭘까?
//        my_info_country_select_tv.text = profileData.country_cd
    }


//
//    val user_name = my_info_name_et.text.toString()
//    val mobile_num = my_info_phone_et.text.toString()
//    val user_EngName = my_info_english_name_et.text.toString()

//    val user_country =


    private fun SaveCheck(){
        //팝업창 띄우기
    }
}