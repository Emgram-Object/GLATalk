package com.example.glatalk_project.Activity;

import android.app.ProgressDialog.show
import android.content.Context
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.glatalk_project.R
import com.example.glatalk_project.Adapter.PageAdapter
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ui_custom_tab.view.*
import kotlinx.android.synthetic.main.ui_popup_custom.*

class MainActivity : AppCompatActivity(){
    private lateinit var mContext: Context

    var myDao = MyDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        myDao.getInfo()


        mContext = applicationContext

        //뷰페이저
        val adapter = PageAdapter(supportFragmentManager)
        main_vp.adapter = adapter
        main_tab.setupWithViewPager(main_vp)

        //커스텀 탭 생성
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

    override fun onBackPressed() {
        val popUp = Popup(this)
        popUp.start("정말 종료하시겠습니까?") //string.xml에서 뽑아내는 걸로 변경 필요
        val OKbtn = popUp.popup.fst_btn
        OKbtn.setOnClickListener {
            finish()
        }

    }


}

