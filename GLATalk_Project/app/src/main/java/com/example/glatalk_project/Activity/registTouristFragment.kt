package com.example.glatalk_project.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_regist.*

class registTouristFragment:Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reg_tour,null)
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var temp = activity as RegistActivity
                var inputId = (activity as RegistActivity).reg_email_et
                var inputPwd = (activity as RegistActivity).reg_pwd_et
                var inputPwdCheck = (activity as RegistActivity).reg_pwd_check_et
                Log.d("TAG", "afterTextChanged: ${inputId.text} ${inputPwd.text} ${inputPwdCheck.text}")
                if (inputId.text.isNotEmpty() && inputPwd.text.isNotEmpty() && inputPwdCheck.text.isNotEmpty()) {
                    if (view.findViewById<EditText>(R.id.reg_tourist_name_et).text.isNotEmpty()
                            //        && view.findViewById<Spinner>(R.id.country_guide_sp).isSelected
                            && view.findViewById<EditText>(R.id.reg_tourist_phone_et).text.isNotEmpty()) {
                        temp.Btn_On()
                        Log.d("TAG", "afterTextChanged: ")
                    }
                } else {
                    temp.Btn_Off()
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        }

        var inputId = (activity as RegistActivity).reg_email_et
        var inputPwd = (activity as RegistActivity).reg_pwd_et
        var inputPwdCheck = (activity as RegistActivity).reg_pwd_check_et

        inputId.addTextChangedListener(textWatcher)
        inputPwd.addTextChangedListener(textWatcher)
        inputPwdCheck.addTextChangedListener(textWatcher)

        view.findViewById<EditText>(R.id.reg_tourist_name_et).addTextChangedListener(textWatcher)
        view.findViewById<EditText>(R.id.reg_tourist_phone_et).addTextChangedListener(textWatcher)


        return view
    }
}