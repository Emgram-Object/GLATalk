package com.example.glatalk_project.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.glatalk_project.BuildConfig
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.fragment_my.*
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

        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        my_logout_btn.setOnClickListener{ goToLogin() }
    }

    private fun goToLogin(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
    private fun goToMyInfo(){
        val intent = Intent(getActivity(), LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToSetting(){
        val intent = Intent(getActivity(), LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goToTerm(){
        val intent = Intent(getActivity(), LoginActivity::class.java)
        startActivity(intent)
    }
}