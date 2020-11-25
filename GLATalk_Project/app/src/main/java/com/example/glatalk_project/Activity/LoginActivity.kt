package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Data.UserData
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.MoveActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.util.TextUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), MoveActivity {

    private var userDAO = UserDAO
    var input_user_email: String = ""
    var input_user_pwd: String = ""
    var userData = UserData()
    var tokenData = TokenData
    var loginResult: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        var inputId: EditText = login_email_et
        var inputPwd: EditText = login_pwd_et


        var textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (inputId.text.isNotEmpty() && inputPwd.text.isNotEmpty()) {
                    Btn_On()
                } else {
                    Btn_Off()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        inputId.addTextChangedListener(textWatcher)
        inputPwd.addTextChangedListener(textWatcher)

        login_regist_tv.setOnClickListener {
            toRegist()
        }
        login_find_pwd.setOnClickListener {
            toPwdFind()
        }
        close_btn.setOnClickListener {
            finish()
            //어떤 이유인지는 모르겠지만 안꺼짐..
        } // 누르면 팝업창 떠서 앱을 종료하겠냐고 물어본다음 확인 누르면 프로세스 종료하는 걸로 할 예정임


        login_btn.setOnClickListener(View.OnClickListener {
            if (TextUtil.idVerify(login_email_et.text.toString())) {
                input_user_email = login_email_et.text.toString()
                input_user_pwd = login_pwd_et.text.toString()
                loginNetworking()
            }


        })

    }

    private fun loginNetworking() {

        userDAO.login(loginRequest = LoginRequest(input_user_email, input_user_pwd), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                loginResult = result.toString()
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()
                Log.d("result", "$loginResult")
                if (response.isSuccessful) {
                    if (userData.resultCode == "0") {
                        tokenData.loginToken = result.body.toString()
                        if (login_auto_cb.isChecked) {
                            userDAO.setAutoLogin(true)
                            userDAO.setLoginToken(TokenData.loginToken.toString())
                            Log.d("loginToken3", "${TokenData.loginToken}")
                        }
                        MyDao.getInfo(this@LoginActivity)
                        move()
                    } else {
                        Log.d("result", "${userData.resultCode}")
                        Log.d("result", "${userData.desc}")
                    }
//                    MyDao.getInfo(this@LoginActivity)
                }
            }
        })
    }

    private fun Btn_On() {
        //색 바꾸기
        login_btn.isEnabled = true
        login_btn.setBackgroundResource(R.drawable.rounded_square)

    }

    private fun Btn_Off() {
        login_btn.isEnabled = false
        login_btn.setBackgroundResource(R.drawable.rounded_square_dim)
    }


    private fun goHome() {

        val intentAct = Intent(this, MainActivity::class.java)
        startActivity(intentAct)
        finish()
        //로그인한 계정 정보에 따라서 가이드 홈화면/관광객 홈화면 구분해서 넘어가기
    }

    private fun toRegist() {
        val intentAct = Intent(this, RegistActivity::class.java)
        startActivity(intentAct)
    }

    private fun toPwdFind() {
        val intentAct = Intent(this, PwdFindActivity::class.java)
        startActivity(intentAct)
    }


    override fun move() {
        goHome()
    }
}





