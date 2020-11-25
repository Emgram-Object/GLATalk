package com.example.glatalk_project.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.PwdData
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.util.TextUtil
import kotlinx.android.synthetic.main.activity_login.*
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

        var currentPwd:EditText = pwd_change_current_et
        var newPwd:EditText = pwd_change_new_et
        var newPwdCheck:EditText = pwd_change_check_et


        var textWatcher = object : TextWatcher {
               override fun afterTextChanged(s: Editable) {
                if (newPwd.text.isNotEmpty() && currentPwd.text.isNotEmpty()&& newPwdCheck.text.isNotEmpty()) {
                    Btn_On()
                } else {
                    Btn_Off()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        newPwd.addTextChangedListener(textWatcher)
        newPwdCheck.addTextChangedListener(textWatcher)
        currentPwd.addTextChangedListener(textWatcher)


        pwd_change_ok.setOnClickListener {
            current = pwd_change_current_et.text.toString()
            new = pwd_change_new_et.text.toString()
            if (TextUtil.pwdVerify(pwd_change_new_et.text.toString())
                    and (new == pwd_change_check_et.text.toString())){
                changePwdNetworking()
                gotoMyInfo()
            }
            Log.d("Wrong", "비밀번호 일치 안함")
        }
        //통신

        //
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

    private fun Btn_On() {
        //색 바꾸기
        pwd_change_ok.isEnabled = true
        pwd_change_ok.setBackgroundResource(R.color.primary_color)

    }


    private fun Btn_Off() {
        pwd_change_ok.isEnabled = false
        pwd_change_ok.setBackgroundResource(R.color.square_dim_color)
    }


    fun gotoMyInfo() {
        val intent = Intent(this, MyInfoActivity::class.java)
        startActivity(intent)
        finish()
    }
}