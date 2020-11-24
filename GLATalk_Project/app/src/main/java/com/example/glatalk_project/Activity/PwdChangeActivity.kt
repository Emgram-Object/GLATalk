package com.example.glatalk_project.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.PwdData
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.MainApplication
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.view.Popup
import com.example.glatalk_project.view.TitleView
import kotlinx.android.synthetic.main.activity_pwd_change.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.glatalk_project.view.TitleView.*
import kotlinx.android.synthetic.main.ui_common_title.*
import kotlinx.android.synthetic.main.ui_common_title.view.*
import kotlinx.android.synthetic.main.ui_popup_custom.*


class PwdChangeActivity : AppCompatActivity() {

    private var myDao = MyDao
    var current:String = ""
    var new:String = "rkdalsdk7981"
    var pwdData = PwdData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwd_change)

        pwd_change_ok.setOnClickListener {
            current = pwd_change_current_et.text.toString()
            new = pwd_change_new_et.text.toString()
            changePwdNetworking()
            finishPwdChange()
        }
        //통신

        //
//        C.TitleBackBtn.poptext = "${MainApplication.getString(R.string.)}"
        C.TitleBackBtn.poptext = "변경사항이 저장되지 않습니다.\n이전화면으로 돌아가시겠습니까?"
         }

    private fun changePwdNetworking(){
        Log.d("login", "${TokenData.loginToken}")
        myDao.change_pwd(pwdRequest = PwdRequest(current,new), callback = object :Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                pwdData.resultCode = result.resultCode.toString()
                pwdData.desc =result.desc.toString()
                Log.d("TAG", "${response.headers().get("Authorization")}")
                if (response.isSuccessful){
                    Log.d("result", "${pwdData.resultCode}")
                    Log.d("result", "${pwdData.desc}")
                }
            }

        })
    }

    fun finishPwdChange() {
        val intent = Intent(this, MyInfoActivity::class.java)
        startActivity(intent)
    }

    fun goback(){
        val popUp = Popup(this)
        popUp.start("${C.TitleBackBtn.poptext}")
        val OKbtn = popUp.popup.fst_btn
        OKbtn.setOnClickListener {
            finish()
            //이거 finishPwdChange()했더니 정보화면에서 뒤로가기 눌렀을 때 다시 변경화면 떠서 finish로 바꿈 전체적으로 이거 바꿔야 할듯
        }
    }

    override fun onBackPressed() {
       goback()
        C.TitleBackBtn.closeOR = true
    }

}

