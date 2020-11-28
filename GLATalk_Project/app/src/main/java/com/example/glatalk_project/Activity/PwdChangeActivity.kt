package com.example.glatalk_project.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.glatalk_project.Model.MyDao
import com.example.glatalk_project.Data.PwdData
import com.example.glatalk_project.R
import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.constant.C
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.request.PwdRequest
import com.example.glatalk_project.util.TextUtil
import com.example.glatalk_project.view.Popup
import kotlinx.android.synthetic.main.activity_pwd_change.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.ui_popup_custom.*


class PwdChangeActivity : AppCompatActivity() {

    private var myDao = MyDao
    var current: String = ""
    var new: String = ""
    var pwdData = PwdData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pwd_change)

        common_title_pwd_change.setTitle(getString(R.string.title_pwdchange))

        var currentPwd: EditText = pwd_change_current_et
        var newPwd: EditText = pwd_change_new_et
        var newPwdCheck: EditText = pwd_change_check_et

        var textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (newPwd.text.isNotEmpty() && currentPwd.text.isNotEmpty() && newPwdCheck.text.isNotEmpty()) {
                    Btn_On()
                } else {
                    Btn_Off()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }

        newPwd.addTextChangedListener(textWatcher)
        newPwdCheck.addTextChangedListener(textWatcher)
        currentPwd.addTextChangedListener(textWatcher)

        pwd_change_ok.setOnClickListener {
            current = pwd_change_current_et.text.toString()
            new = pwd_change_new_et.text.toString()
            if (pwd_change_new_et.text.toString().length >= 8) {
                if (new == pwd_change_check_et.text.toString()) {
                    changePwdNetworking()
                    gotoMyInfo()
                } else {
                    Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "비밀번호는 8자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        //통신

        //
        C.TitleBackBtn.poptext = "변경사항이 저장되지 않습니다.\n이전화면으로 돌아가시겠습니까?"
    }

    private fun changePwdNetworking() {
        Log.d("login", "${TokenData.loginToken}")
        myDao.change_pwd(pwdRequest = PwdRequest(current, new), callback = object : Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                var result = response.body()!!
                pwdData.resultCode = result.resultCode.toString()
                pwdData.desc = result.desc.toString()
                Log.d("TAG", "${response.headers().get("Authorization")}")
                if (response.isSuccessful) {
                    Log.d("result", "${pwdData.resultCode}")
                    Log.d("result", "${pwdData.desc}")
                }
            }

        })
    }

    private fun Btn_On() {
        //색 바꾸기
        pwd_change_ok.isEnabled = true
        pwd_change_ok.setBackgroundResource(R.color.primary_color)

    }


    private fun Btn_Off() {
        pwd_change_ok.isEnabled = false
        pwd_change_ok.setBackgroundResource(R.color.square_dim_color)
    }


    fun gotoMyInfo() {
        val intent = Intent(this, MyInfoActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goback() {
        val popUp = Popup(this)
        popUp.start("${C.TitleBackBtn.poptext}")
        val pop_up = popUp.popup
        val OKbtn = pop_up.fst_btn
        OKbtn.setOnClickListener {
            pop_up.dismiss()
            finish()
            //이거 finishPwdChange()했더니 정보화면에서 뒤로가기 눌렀을 때 다시 변경화면 떠서 finish로 바꿈 전체적으로 이거 바꿔야 할듯
        }
    }

    override fun onBackPressed() {
        goback()
        C.TitleBackBtn.closeOR = true
    }

}

