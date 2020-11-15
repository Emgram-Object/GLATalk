package com.example.glatalk_project.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.Model.UserDAO.userVO
import com.example.glatalk_project.R
import com.example.glatalk_project.R.array.country_cd_list
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.UserRequest
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

//    lateinit var country_guide_sp :Spinner
//    val country_cd = resources.getStringArray(R.array.country_cd_list)
//    val sp_adapter = ArrayAdapter.createFromResource(this, country_cd_list, android.R.layout.simple_dropdown_item_1line)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        touristFragment = registTouristFragment()
        guideFragment = registGuideFragment()

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


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
                finishRegister()

            } else if (reg_tour_radio_bnt.isChecked) {
                getTouristData()
                Log.d("log", userDAO.userVO.toString())
                addNetworking()
                finishRegister()
            }
        })

    }

//    fun country_guide(){
//        country_guide_sp.adapter = sp_adapter
//        country_guide_sp.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                Log.d("spinner", "onNothingSelected: ")
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                when(position){
//                    0 -> "힌국"
//                }
//            }
//
//        }스피너 만들다가 귀찮아서 일단 스탑
//
//    }

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

    fun addNetworking() {

        UserDAO.add(userRequest = UserRequest(userDAO.userVO.user_name,userDAO.userVO.user_email,userDAO.userVO.user_type,userDAO.userVO.user_pwd,
        userDAO.userVO.phone_number,userDAO.userVO.country_cd,userDAO.userVO.guide_info,userDAO.userVO.guide_time,userDAO.userVO.ad_agree),callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("add", "fail")
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("add", "success")
                //잘 보내졌는지 확인하고 싶음..
            }
        })


    }

    fun getGuideData() {
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "guide"
        userDAO.userVO.user_name = reg_name_et.text.toString()
        // userDAO.userVO.country_cd = reg_country_select_tv.text.toString()  -> 스피너 값 받는거 함수만들어서 따로 처리 해서 추가.
        userDAO.userVO.country_cd = "Kr"
        userDAO.userVO.phone_number = reg_phone_et.text.toString()
        userDAO.userVO.guide_info = reg_guide_info_et.text.toString()
        userDAO.userVO.guide_time = reg_time_et.text.toString()
        userDAO.userVO.ad_agree = true
    }

    fun getTouristData() {
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "tourist"
        userDAO.userVO.user_name = reg_tourist_name_et.text.toString()
        userDAO.userVO.country_cd = "Kr"
        // userDAO.userVO.country_cd = reg_tourist_country_select_tv.text.toString()  -> 스피너 값 받는거 함수만들어서 따로 처리 해서 추가.
        userDAO.userVO.phone_number = reg_tourist_phone_et.text.toString()
        userDAO.userVO.ad_agree = true

    }

    fun finishRegister(){
        val intentAct = Intent(this, LoginActivity::class.java)
        startActivity(intentAct)
        finish()
        //로그인으로 되돌아가는 부
    }

}