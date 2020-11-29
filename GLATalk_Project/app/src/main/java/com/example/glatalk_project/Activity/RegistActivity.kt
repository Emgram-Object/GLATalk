package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.R.layout.activity_regist
import com.example.glatalk_project.network.data.response.BaseResponse
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
    lateinit var textWatcher: TextWatcher

//    lateinit var country_guide_sp :Spinner
//    val country_cd = resources.getStringArray(R.array.country_cd_list)
//    val sp_adapter = ArrayAdapter.createFromResource(this, country_cd_list, android.R.layout.simple_dropdown_item_1line)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_regist)

        common_title_regist.setTitle(getString(R.string.title_regist))

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

        //프래그먼트 전환 반복하면 에러남.

        reg_next_btn.setOnClickListener(View.OnClickListener {
            if (reg_guide_radio_bnt.isChecked) {
                getGuideData()

//<<<<<<< HEAD
//                addNetworking()
//                gotoLogin()
//
//            } else if (reg_tour_radio_bnt.isChecked) {
//                getTouristData()
//                addNetworking()
//                gotoLogin()
//=======
//                if (reg_pwd_et.text.toString() == reg_pwd_check_et.text.toString()) {
//                    addNetworking()
//                    gotoRegComplete()
//=======
                if (reg_pwd_et.text.toString().length >= 8) {
                    if (reg_pwd_et.text.toString() == reg_pwd_check_et.text.toString()) {
                        addNetworking()
                        gotoRegComplete()
                    } else {
                        Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(this, "비밀번호는 8자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()

                }
            } else if (reg_tour_radio_bnt.isChecked) {
                getTouristData()
                if (reg_pwd_et.text.toString().length >= 8) {
                    if (reg_pwd_et.text.toString() == reg_pwd_check_et.text.toString()) {
                        addNetworking()
                        gotoRegComplete()
                    } else {
                        Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(this, "비밀번호는 8자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                }

                Log.d("wrong", "실패실패")



            }
        })

    }

    fun changeTouristFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl, touristFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

//        touristFragment.reg_tourist_name_et.addTextChangedListener(textWatcher)
//        touristFragment.reg_tourist_phone_et.addTextChangedListener(textWatcher)
//        guideFragmentManager.country_guide_sp.addOnAttachStateChangeListener()
    }

    fun changeGuideFragement() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl, guideFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }



//    fun country_gd(){
////        country_sp_g = reg_guide_country_select_sp
//        val country_sp_g = country_guide_sp
//        val countryList = CountryAdapter().countryList
//        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countryList)
//        country_sp_g.adapter = arrayAdapter
//
//        country_sp_g.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////                parent?.getItemAtPosition(position)
////                Log.d("nm", "${countryList[position]}")
//                userDAO.userVO.country_cd = C.NationalCode.values()[position].country_cd
//                Log.d("코드", "${UserDAO.userVO.country_cd}")
//            }
//        }

//    }

    fun addNetworking() {

        UserDAO.add(userRequest = UserRequest(userDAO.userVO.user_name, userDAO.userVO.user_email, userDAO.userVO.user_type, userDAO.userVO.user_pwd,
                userDAO.userVO.phone_number, userDAO.userVO.country_cd, userDAO.userVO.guide_info, userDAO.userVO.guide_time, userDAO.userVO.ad_agree), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("add", "fail")
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Log.d("add", "success")
            }
        })

    }

    fun getGuideData() {
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "guide"
        userDAO.userVO.user_name = reg_guide_name_et.text.toString()
        // userDAO.userVO.country_cd = reg_country_select_tv.text.toString()  -> 스피너 값 받는거 함수만들어서 따로 처리 해서 추가.
        userDAO.userVO.country_cd
        userDAO.userVO.phone_number = reg_phone_et.text.toString()
        userDAO.userVO.guide_info = reg_guide_info_et.text.toString()
        userDAO.userVO.guide_time = reg_time_et.text.toString()
        userDAO.userVO.ad_agree = true

        Log.d("regist_guide", "${userDAO.userVO.country_cd}")
    }

    fun getTouristData() {
        userDAO.userVO.user_email = reg_email_et.text.toString()
        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
        userDAO.userVO.user_type = "tourist"
        userDAO.userVO.user_name = reg_tourist_name_et.text.toString()
//        userDAO.userVO.country_cd = "ko"
        userDAO.userVO.country_cd
        userDAO.userVO.phone_number = reg_tourist_phone_et.text.toString()
        userDAO.userVO.ad_agree = true

        Log.d("regist_tour", "${userDAO.userVO.country_cd}")

    }

    internal fun Btn_On() {
        //색 바꾸기
        reg_next_btn.isEnabled = true
        reg_next_btn.setBackgroundResource(R.color.primary_color)

    }

    internal fun Btn_Off() {
        reg_next_btn.isEnabled = false
        reg_next_btn.setBackgroundResource(R.color.square_dim_color)
    }


    fun gotoRegComplete() {
        val intentAct = Intent(this, RegistCompleteActivity::class.java)
        startActivity(intentAct)
        Intent.FLAG_ACTIVITY_NO_HISTORY
        finish()
        //로그인으로 되돌아가는 부분
    }

}
