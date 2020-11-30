package com.example.glatalk_project.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Activity.LoginActivity
import com.example.glatalk_project.Activity.LogoutActivity
import com.example.glatalk_project.Activity.MyInfoActivity
import com.example.glatalk_project.Activity.SettingActivity
import com.example.glatalk_project.BuildConfig
import com.example.glatalk_project.Data.ProfileData
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.fragment_my.view.*
import kotlinx.android.synthetic.main.ui_popup_custom.*


class MyFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        val version: String = BuildConfig.VERSION_NAME

        Log.d("my_token", "${TokenData.loginToken}")

        //이후 코드 구현
        view.my_profile_name_tv.text = ProfileData.user_name
        view.my_profile_email_tv.text = ProfileData.user_email

        view.my_version_ver.text = version //앱 버전정보 출력
        view.my_profile_cl.setOnClickListener {
            goToMyInfo()

        }
        view.my_setting_cl.setOnClickListener { goToSetting() }
        view.my_term_cl.setOnClickListener { goToTerm() }

        view.my_logout_btn.setOnClickListener {
            LogoutActivity.doLogout()
            goToLogin()
        }
        C.TitleBackBtn.poptext = "앱을 종료하시겠습니까?"
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                        val popUp = Popup(context as Activity)
                        popUp.start("${C.TitleBackBtn.poptext}")
                        val OKbtn = popUp.popup.fst_btn
                        OKbtn.setOnClickListener {
                            (context as? Activity)?.finish()
                        }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    private fun goToLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        Intent.FLAG_ACTIVITY_CLEAR_TOP
        (context as? Activity)?.finish()
    }

    private fun goToMyInfo() {
        val intent = Intent(context, MyInfoActivity::class.java)
        startActivity(intent)
//        Intent.FLAG_ACTIVITY_SINGLE_TOP
        C.TitleBackBtn.closeOR = true

    }

    private fun goToSetting() {
        val intent = Intent(context, SettingActivity::class.java)
        startActivity(intent)
        C.TitleBackBtn.closeOR = false
    }

    private fun goToTerm() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}