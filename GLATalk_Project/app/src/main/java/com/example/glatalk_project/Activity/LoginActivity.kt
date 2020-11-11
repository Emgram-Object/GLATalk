package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private var userDAO = UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_regist_tv.setOnClickListener{
            val intent = Intent(this,RegistActivity::class.java)
            startActivity(intent)
        }
            //넘겨주기 //request
            //하고 나서 홈화면으로 가기
//            goHome()
//            EmptyCheck()
//            if (login_auto_cb.isChecked) {
//                AutoLogin()
//            }

//
//        login_find_pwd.setOnClickListener {
//            pwd_find()
//        }
//
//        login_regist_tv.setOnClickListener {
//            toregist()
//        }
//        if (login_email_et.isNotBlank() && user_pwd.isNotBlank()) {
//            //로그인 버튼 활성화
//            Btn_Enable()
//
//        }

        val user_email = login_email_et.text.toString()
        val user_pwd = login_pwd_et.text.toString()


        UserDAO.login(loginRequest = LoginRequest("", ""), callback = object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("d", "onFailure: fail")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("ㅇ", "onResponse: 성공  ")
            }
        })
    }
}

//private fun pwd_find() {
//    val intentAct = Intent(this, PwdFindActivity::class.java)
//    startActivity(intentAct)
//}
//
//private fun toregist() {
//    val intentAct = Intent(this, RegistActivity::class.java)
//    startActivity(intentAct)
//}
//
//private fun goHome() {
//    //로그인한 계정 정보에 따라서 가이드 홈화면/관광객 홈화면 구분해서 넘어가기
//}
//
//private fun AutoLogin() {
//    //sharedPreferences 연결하기
//
//}
//
//fun EmptyCheck() {
//    if (user_email.isBlank() || user_pwd.isBlank()) {
//        //팝업창 띄우기
//    }
//}
//
//private fun Btn_Enable() {
//    if (login_btn.isEnabled) {
//        //색 바꾸기
//        login_btn.setBackgroundResource(R.drawable.rounded_square)
//    }
//}