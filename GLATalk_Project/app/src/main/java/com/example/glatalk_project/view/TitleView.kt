package com.example.glatalk_project.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import com.example.glatalk_project.Activity.*
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
//        setFinishListener()
        common_back_btn.setOnClickListener { (context as Activity).onBackPressed() } //지멘이 코드 최적화시켜줌 갓 지-멘
    }

    fun setTitle(str: String) {
        title_tv.text = str
    }

    fun setModifyBtn() {
        change_tv.visibility = View.VISIBLE

        change_tv.setOnClickListener {
            C.TitleBackBtn.closeOR = false
            val intent = Intent(context, InfoChangeActivity::class.java)
//            Intent.FLAG_ACTIVITY_NO_HISTORY
            (context as Activity).finish()
            (context as? Activity)?.startActivity(intent)
        }
    }

//    fun disableModifyBtn(){
//        change_tv.visibility = View.VISIBLE
//    }
//
//    fun setFinishListener() {
//        if(C.TitleBackBtn.closeOR) {
//            common_back_btn.setOnClickListener {
//                Intent.FLAG_ACTIVITY_NO_HISTORY
//                (context as? Activity)?.finish()
//
//            }
//        }
//        else{
//            common_back_btn.setOnClickListener {
//                val popUp = Popup(context as Activity)
//                popUp.start("${C.TitleBackBtn.poptext}")
//                val pop_up = popUp.popup
//                val OKbtn = pop_up.fst_btn
//
//                if(C.TitleBackBtn.CancelBack) {
//                    OKbtn.setOnClickListener {
//                        C.TitleBackBtn.CancelBack = false
//                        pop_up.dismiss()
//                        finishAffinity(context as Activity)
//                        Intent.FLAG_ACTIVITY_NO_HISTORY
//
//                        val intent = Intent(context as Activity, SplashActivity::class.java)
//                        (context as? Activity)?.startActivity(intent)
//                        System.exit(0)      //종료 후 재시작
//                    }
//
//                    val cancelBT = pop_up.snd_btn
//                    cancelBT.setOnClickListener {
//                        C.TitleBackBtn.CancelBack = false
//                        pop_up.dismiss()
//                        (context as? Activity)?.finish()
//                    }
//                }
//                    else{
//                        OKbtn.setOnClickListener {
//                            pop_up.dismiss()
//                            Intent.FLAG_ACTIVITY_NO_HISTORY
//                            (context as? Activity)?.finish()
//                        }
//                    }
//
//            }
//        }
//    }
}
