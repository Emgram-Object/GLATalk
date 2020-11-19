package com.example.glatalk_project.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.glatalk_project.R

import kotlinx.android.synthetic.main.ui_common_title.view.*

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
//    change_tv.visibility = View.VISIBLE
//
//    change_tv.setOnClickListener {
//        InfoChange()
//    }
    fun setFinishListener() {
        common_back_btn.setOnClickListener {
            (context as? Activity)?.finish()
        }
    }
}
