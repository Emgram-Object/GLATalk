package com.example.glatalk_project.view

import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.app.Dialog
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.ui_popup_custom.*
import org.w3c.dom.Text

class Popup(context: Context):Dialog(context){

    val popup = Dialog(context)

    fun start() {
        popup.requestWindowFeature(Window.FEATURE_NO_TITLE)
        popup.setContentView(R.layout.ui_popup_custom)
        popup.setCancelable(false)

        val lbdesc = popup.findViewById(R.id.popup_message_tv) as TextView
        val btnOk = popup.findViewById(R.id.fst_btn) as TextView

        val btnCancel = popup.findViewById(R.id.snd_btn) as TextView
        btnCancel.setOnClickListener {
            popup.dismiss()
        }
        popup.show()

    }


}