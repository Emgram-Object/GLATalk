package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.PwdData
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import kotlinx.android.synthetic.main.activity_pwd_change.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PwdChangeActivity : AppCompatActivity() {

    private var myDao = MyDao
    var current:String = ""
    var new:String = ""
    var pwdData = PwdData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwd_change)

        common_title_pwd_change.setTitle("비밀번호 변경")

        pwd_change_ok.setOnClickListener {
            current = pwd_change_current_et.text.toString()
            new = pwd_change_new_et.text.toString()
            changePwdNetworking()
            finishPwdChange()
        }
         }

    private fun changePwdNetworking(){
        Log.d("login", "${TokenData.loginToken}")
        myDao.change_pwd(pwdRequest = PwdRequest(current,new), callback = object :Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                pwdData.resultCode = result.resultCode.toString()
                pwdData.desc =result.desc.toString()
                Log.d("TAG", "${response.headers().get("Authorization")}")
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