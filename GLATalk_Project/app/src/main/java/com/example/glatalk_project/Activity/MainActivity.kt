package com.example.glatalk_project.Activity;

import android.app.ProgressDialog.show
import android.content.Context
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.PageAdapter
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.util.LocaleHelper
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ui_custom_tab.view.*
import kotlinx.android.synthetic.main.ui_popup_custom.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(){
    private lateinit var mContext: Context

    var myDao = MyDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext

        //뷰페이저
        val adapter = PageAdapter(supportFragmentManager)
        main_vp.adapter = adapter
        main_tab.setupWithViewPager(main_vp)

        //커스텀 탭 생성
        main_tab.getTabAt(0)?.customView = creatView("home")
        main_tab.getTabAt(1)?.customView = creatView("my")
        C.TitleBackBtn.poptext = getString(R.string.popup_quit)
    }

    private fun creatView(tabName: String): View {
        var tabView = LayoutInflater.from(mContext).inflate(R.layout.ui_custom_tab,null)

        tabView.custom_tab_tv.text = tabName
        when (tabName) {
            "home" -> {tabView.custom_tab_icon.setImageResource(R.drawable.icon_home_selector)
                return tabView
            }
            "my" -> {
                tabView.custom_tab_icon.setImageResource(R.drawable.icon_my_selector)
                return tabView
            }
            else -> {
                return tabView
            }
        }
    }

    override fun onBackPressed() {
        val popUp = Popup(this)
        popUp.start("${C.TitleBackBtn.poptext}")
        val OKbtn = popUp.popup.fst_btn
        OKbtn.setOnClickListener {
            moveTaskToBack(true)
            finish()
            exitProcess(0)

        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase!!))
    }

}

