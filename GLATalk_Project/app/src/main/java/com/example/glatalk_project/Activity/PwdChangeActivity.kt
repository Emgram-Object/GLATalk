package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.PwdData
import com.example.glatalk_project.R
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import kotlinx.android.synthetic.main.activity_pwd_change.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PwdChangeActivity : AppCompatActivity() {

    private var myDao = MyDao
    var input_current_pwd:String = ""
    var input_new_pwd:String = ""
    var pwdData = PwdData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwd_change)

        pwd_change_ok.setOnClickListener {
            changePwdNetworking()
            finishPwdChange()
        }
        //통신

        //
        input_current_pwd = pwd_change_current_et.text.toString()
        input_new_pwd = pwd_change_new_et.text.toString()
    }

    fun changePwdNetworking(){
        myDao.change_pwd(pwdRequest = PwdRequest(input_current_pwd,input_new_pwd), callback = object :Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

                Log.d("header", "${TokenData.loginToken}")
                var result = response.body()!!
                Log.d("header", "$input_current_pwd")
                Log.d("header", "$input_new_pwd")
                pwdData.resultCode = result.resultCode.toString()
                pwdData.desc =result.desc.toString()
                if (response.isSuccessful){
                    Log.d("result", "${pwdData.resultCode}")
                    Log.d("result", "${pwdData.desc}")
                }
            }

        })
    }

    fun finishPwdChange() {
        val intent = Intent(this, MyInfoActivity::class.java)
        startActivity(intent)
    }
}