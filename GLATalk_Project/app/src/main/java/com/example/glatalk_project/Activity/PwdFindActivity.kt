package com.example.glatalk_project.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.UserData
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.PwdFindRequest
import kotlinx.android.synthetic.main.activity_find_pwd.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PwdFindActivity:AppCompatActivity() {


    private var userDAO = UserDAO
    var input_user_email:String= " "
    var userData = UserData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_pwd)

        var inputId : EditText = find_pwd_id_et

        find_pwd_back_btn.setOnClickListener {
            finish_find_pwd()
        }

        find_pwd_send.setOnClickListener {
            input_user_email = find_pwd_id_et.text.toString()
            send_find_pwd()
        }

        var textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (inputId.text.isNotEmpty()) {
                    Btn_On()
                } else {
                    Btn_Off()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        inputId.addTextChangedListener(textWatcher)

    }


    fun send_find_pwd(){

        userDAO.findPwd(pwdFindRequest = PwdFindRequest(input_user_email), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()

                if (response.isSuccessful) {
                    if (userData.resultCode == "0") {
                        Log.d("result", "${userData.resultCode}")
                        Log.d("result", "${userData.desc}")
                        //발송완료 팝업창
                    } else {
                        //등록뙨 이메일이 아닙니다 팝업창
                        Log.d("result", "${userData.resultCode}")
                        Log.d("result", "${userData.desc}")
                    }
                }
            }
        })
    }

    private fun Btn_On() {
        //색 바꾸기
        find_pwd_send.isEnabled = true
        find_pwd_send.setBackgroundResource(R.drawable.rounded_square)

    }

    private fun Btn_Off() {
        find_pwd_send.isEnabled = false
        find_pwd_send.setBackgroundResource(R.drawable.rounded_square_dim)
    }


    fun finish_find_pwd(){
        val intentAct = Intent(this, LoginActivity::class.java)
        Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intentAct)
        finish()
    }

}
