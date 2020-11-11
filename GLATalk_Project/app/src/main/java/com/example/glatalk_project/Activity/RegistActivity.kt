package com.example.glatalk_project.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.Model.UserVO
import com.example.glatalk_project.R
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.request.UserRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import kotlinx.android.synthetic.main.activity_regist.*
import kotlinx.android.synthetic.main.fragment_reg_guide.*
import kotlinx.android.synthetic.main.fragment_reg_tour.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegistActivity : AppCompatActivity() {

    lateinit var guideFragment: registGuideFragment
    lateinit var touristFragment: registTouristFragment
    private var userDAO = UserDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        touristFragment = registTouristFragment()
        guideFragment = registGuideFragment()


        reg_tour_radio_bnt.setOnClickListener(View.OnClickListener {
            reg_guide_radio_bnt.isChecked = false
            changeTouristFragment()
        })
        reg_guide_radio_bnt.setOnClickListener(View.OnClickListener {
            reg_tour_radio_bnt.isChecked = false
            changeGuideFragement()
        })


        reg_next_btn.setOnClickListener(View.OnClickListener {
            if (reg_guide_radio_bnt.isChecked) {
                getGuideData()
                Log.d("log", userDAO.userVO.toString())
                addNetworking()

            }else if(reg_tour_radio_bnt.isChecked){
                getTouristData()
                Log.d("log", userDAO.userVO.toString())
                addNetworking()
            }
        })
    }

    fun changeTouristFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl, touristFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

    }

    fun changeGuideFragement() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl, guideFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    fun getGuideData() {
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "guide"
        userDAO.userVO.user_name = reg_name_et.text.toString()
        // userDAO.userVO.country_cd = reg_country_select_tv.text.toString()  -> 스피너 값 받는거 함수만들어서 따로 처리 해서 추가.
        userDAO.userVO.country_cd ="ko"
        userDAO.userVO.phone_number = reg_phone_et.text.toString()
        userDAO.userVO.guide_info = reg_guide_info_et.text.toString()
        userDAO.userVO.guide_time = reg_time_et.text.toString()
        userDAO.userVO.ad_agree =true
    }
    fun getTouristData(){
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "tourist"
        userDAO.userVO.user_name = reg_tourist_name_et.text.toString()
        userDAO.userVO.country_cd ="ko"
        // userDAO.userVO.country_cd = reg_tourist_country_select_tv.text.toString()  -> 스피너 값 받는거 함수만들어서 따로 처리 해서 추가.
        userDAO.userVO.phone_number = reg_tourist_phone_et.text.toString()
        userDAO.userVO.ad_agree = true

    }
    fun addNetworking(){
        UserDAO.add( userRequest = UserRequest("","","","","","","","",true)
                , callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("add", "fail")
//                Log.d("add", "onFailure: ${execute.code()} ${execute.message()}")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("add", "success")
            }

        })
    }
}