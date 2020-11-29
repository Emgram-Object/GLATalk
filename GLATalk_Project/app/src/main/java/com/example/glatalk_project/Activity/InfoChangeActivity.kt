package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Adapter.CountryAdapter
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.data.request.ProfileRequest
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_my_info_change.*
import kotlinx.android.synthetic.main.ui_popup_custom.*
import org.json.JSONObject
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

        Log.d("infoChange_token", "${TokenData.loginToken}")


        common_title_info_change.setTitle(getString(R.string.title_modiy_myinfo))
        C.TitleBackBtn.poptext = "변경사항이 저장되지 않습니다.\n이전화면으로 돌아가시겠습니까?"
        setTexts()

        modify_ok_btn.setOnClickListener {
            changeMyInfo()
            Thread.sleep(100)
            gotoMy()
        }

        if (input_user_name.isBlank() || input_phone_num.isBlank() || input_user_EngName.isBlank()) {
            modify_ok_btn.setBackgroundResource(R.drawable.rounded_square_dim)
        }
    }

    private fun setTexts() {
        my_info_chg_name_et.setText(ProfileData.user_name)
        my_info_chg_phone_et.setText(ProfileData.phone_number)
//user english name은 뭘까?
//        my_info_chg_country_select_tv.text = ProfileData.country_cd
    }

    private fun getTexts() {
        input_user_name = my_info_chg_name_et.text.toString()
        input_phone_num = my_info_chg_phone_et.text.toString()
        input_user_country

//        userDAO.userVO.user_email = reg_email_et.text.toString()
//        userDAO.userVO.user_pwd = reg_pwd_et.text.toString()
//        userDAO.userVO.pwd_check = reg_pwd_check_et.text.toString()
//        userDAO.userVO.user_type = "tourist"
//        userDAO.userVO.user_name = reg_tourist_name_et.text.toString()
//        userDAO.userVO.country_cd
//        userDAO.userVO.phone_number = reg_tourist_phone_et.text.toString()
    }

    private fun Country_sp() {
        val countryList = CountryAdapter().countryList
        val country_sp = findViewById<Spinner>(R.id.my_info_country_select_sp)
        val arrayAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, countryList)
        country_sp.adapter = arrayAdapter

        country_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                parent?.getItemAtPosition(position)
//                Log.d("nm", "${countryList[position]}")
                input_user_country = C.NationalCode.values()[position].country_cd.toString()
            }
        }
    }


    private fun changeMyInfo() {
        getTexts()
        Log.d("TAG", "changeMyInfo: ${input_user_name} ${input_phone_num} ${input_user_country}")
        myDao.modify_info(profileRequest = ProfileRequest(input_user_name, input_phone_num, input_user_country), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("fail", "실패")
                }

                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.d("tag", "success")
                    if(response.isSuccessful){
                        var result = response.body()!!
                        var resultCode = result.resultCode
                        var body = result.body.toString()
                        var jsonObject: JSONObject = JSONObject(body)

                        Log.d("TAG1", "onResponse: $resultCode")
                        Log.d("TAG1", "$resultCode, ${result.desc}")
                        Log.d("TAG2", "Response: $body")
                    }
                    else{
                        Log.d("Fail", "실패")
                    }
                }
            }
        )
    }




    private fun gotoMy() {
        val intent = Intent(this, MyInfoActivity::class.java) //마이메뉴 화면으로 넘어가기
        Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
        C.TitleBackBtn.closeOR = true
        finish()
    }

    fun goback(){
        val popUp = Popup(this)
        popUp.start("${C.TitleBackBtn.poptext}")
        val pop_up = popUp.popup
        val OKbtn = pop_up.fst_btn
        OKbtn.setOnClickListener {
            pop_up.dismiss()
            finish()
        }
    }

    override fun onBackPressed() {
        goback()
        C.TitleBackBtn.closeOR = true
    }

}