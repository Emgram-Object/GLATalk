package com.example.glatalk_project.Activity;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater
import android.view.View
import com.example.glatalk_project.R
import com.example.glatalk_project.core.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ui_custom_tab.view.*

class MainActivity : AppCompatActivity(){
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext

        val adapter = PageAdapter(supportFragmentManager)
        main_vp.adapter = adapter
        main_tab.setupWithViewPager(main_vp)

        main_tab.getTabAt(0)?.setCustomView(creatView("home"))
        main_tab.getTabAt(1)?.setCustomView(creatView("my"))
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
}

