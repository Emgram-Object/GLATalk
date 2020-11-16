package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.TokenData
import com.example.glatalk_project.UserData
import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.data.request.LoginRequest
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {

    private var userDAO = UserDAO
    var input_user_email: String = ""
    var input_user_pwd: String = ""
    var userData = UserData()
    var tokenData = TokenData
//    var loginResult: String = ""
    lateinit var input: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        if (login_email_et.text.toString().isNotBlank() && login_pwd_et.text.toString().isNotBlank()) {
//            Btn_Enable()
//           //addTextChangeListener 사용
//        }

        login_regist_tv.setOnClickListener {
            toRegist()
        }
        login_find_pwd.setOnClickListener {
            toPwdFind()
        }
        close_btn.setOnClickListener {
            exitProcess(0)
        } // 누르면 팝업창 떠서 앱을 종료하겠냐고 물어본다음 확인 누르면 프로세스 종료하는 걸로 할 예정임

//            EmptyCheck()
//            if (login_auto_cb.isChecked) {
//                AutoLogin()
//            }

        Btn_Enable()
        login_btn.setOnClickListener(View.OnClickListener {
            input_user_email = login_email_et.text.toString()
            input_user_pwd = login_pwd_et.text.toString()
            loginNetworking()
        })
    }





    private fun loginNetworking() {

        userDAO.login(loginRequest = LoginRequest(input_user_email, input_user_pwd), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
//                loginResult = result.toString()
                userData.resultCode = result.resultCode.toString()
                userData.desc = result.desc.toString()
//                Log.d("result", "$loginResult")
                if (response.isSuccessful) {
                    tokenData.loginToken = result.body.toString()
                    if (userData.resultCode == "0") {
                        goHome()
                    } else {
                        Log.d("result", "${userData.resultCode}")
                        Log.d("result", "${userData.desc}")
                    }
                }
            }
        })
    }

    private fun Btn_Enable() {
        if (login_btn.isEnabled) {
            //색 바꾸기
            login_btn.setBackgroundResource(R.drawable.rounded_square)
        }
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

    //    fun EmptyCheck() {
//        if (input_user_email.isBlank() || input_user_pwd.isBlank()) {
//            //팝업창 띄우기
//        }
//      }
    private fun AutoLogin() {
        //sharedPreferences 연결하기 //토큰 저장

    }
}



