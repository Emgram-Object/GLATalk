package com.example.glatalk_project.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Activity.LoginActivity
import com.example.glatalk_project.Activity.SettingActivity
import com.example.glatalk_project.BuildConfig
import com.example.glatalk_project.Activity.MyInfoActivity
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.fragment_my.view.*

class MyFragment: Fragment(){
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        val version: String = BuildConfig.VERSION_NAME
        //이후 코드 구현

        view.my_version_ver.text = version //앱 버전정보 출력
        view.my_profile_cl.setOnClickListener { goToMyInfo() }
        view.my_setting_cl.setOnClickListener { goToSetting() }
        view.my_term_cl.setOnClickListener { goToTerm() }
        view.my_logout_btn.setOnClickListener{ goToLogin() }

        return  view
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        my_logout_btn.setOnClickListener{ goToLogin() }
//
//    }

    //임시로 LoginActivity로 연결해놓음 다른 액티비티들 만들어지면 수정 ㄱㄱ
    private fun goToLogin(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun goToMyInfo(){
        val intent = Intent(context, MyInfoActivity::class.java)
        startActivity(intent)
    }

    private fun goToSetting(){
        val intent = Intent(context, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun goToTerm(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}