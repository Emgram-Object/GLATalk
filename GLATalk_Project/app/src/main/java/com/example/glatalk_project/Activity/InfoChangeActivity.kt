package com.example.glatalk_project.Activity

import android.bluetooth.le.AdvertiseData
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
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
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

        common_title_info_change.setTitle("내 정보 변경")
        Country_sp()
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
//        my_info_chg_country_select_tv.text = ProfileData.country_cd
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
                input_user_country = C.NationalCode.values()[position].country_cd
                Log.d("코드", "$input_user_country")


            }
        }
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