package com.example.glatalk_project.view

import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.app.Dialog
import com.example.glatalk_project.R

class Popup(context: Context) {
    private val popup = Dialog(context)
    private lateinit var lbdesc: TextView
    private lateinit var btnOk: Button
    private lateinit var btnCancel: Button
    private lateinit var listener: MyDialogOKClickedListener

    fun start(content: String){
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE)
        popup.setContentView(R.layout.ui_popup_custom)
        popup.setCancelable(false)

        lbdesc = popup.findViewById(R.id.popup_message_tv)
        lbdesc.text = content
        btnOk = popup.findViewById(R.id.fst_btn)
        btnOk.setOnClickListener{

        }

        btnCancel = popup.findViewById(R.id.snd_btn)
        btnCancel.setOnClickListener {
            popup.dismiss()
        }
        popup.show()

    }

}