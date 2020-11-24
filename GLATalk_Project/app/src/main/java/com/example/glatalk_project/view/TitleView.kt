package com.example.glatalk_project.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.glatalk_project.Activity.InfoChangeActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.ui_common_title.view.*
import kotlinx.android.synthetic.main.ui_popup_custom.*

class TitleView
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.ui_common_title, this)
        setFinishListener()
    }

    fun setTitle(str: String) {
        title_tv.text = str
    }

    fun setModifyBtn() {
        change_tv.visibility = View.VISIBLE
        C.TitleBackBtn.closeOR = false

        change_tv.setOnClickListener {
            val intent = Intent(context, InfoChangeActivity::class.java)
            (context as? Activity)?.startActivity(intent)

        }
    }

    fun setFinishListener() {
        if(C.TitleBackBtn.closeOR==true) {
            common_back_btn.setOnClickListener {
                (context as? Activity)?.finish()
            }
        }
        else{
            common_back_btn.setOnClickListener {
                val popUp = Popup(context as Activity)
                popUp.start("${C.TitleBackBtn.poptext}")
                val OKbtn = popUp.popup.fst_btn
                OKbtn.setOnClickListener {
                    (context as? Activity)?.finish()
                }
            }
        }
    }
}
