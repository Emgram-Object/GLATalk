package com.example.glatalk_project.view

import android.app.Activity
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.app.Dialog
import android.content.ContextWrapper
import android.util.Log
import android.view.LayoutInflater
import com.example.glatalk_project.Activity.SettingActivity
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.ui_popup_custom.*
import org.w3c.dom.Text

class Popup(context: Context):Dialog(context){

    val popup = Dialog(context)

    fun start(content:String) {
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE)
        popup.setContentView(R.layout.ui_popup_custom)
        popup.setCancelable(false)

        val popupText = popup.findViewById(R.id.popup_message_tv) as TextView
        popupText.text = content
        val btnOk = popup.findViewById(R.id.fst_btn) as TextView

        val btnCancel = popup.findViewById(R.id.snd_btn) as TextView

        if(!C.TitleBackBtn.CancelBack) {
            btnCancel.setOnClickListener {
                popup.dismiss()
            }
        }
        popup.show()
    }
}