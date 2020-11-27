package com.example.glatalk_project.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Adapter.CountryAdapter
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.activity_regist.*

class registGuideFragment : Fragment() {

    lateinit var reg: EditText

    lateinit var textWatcher:TextWatcher

    lateinit var reg_guide_name_et : EditText
    lateinit var reg_phone_et: EditText
    lateinit var reg_guide_info_et: EditText
    lateinit var reg_time_et: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        country_guide_sp.adapter = activity?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, countryList) }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_reg_guide, null)

        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var temp = activity as RegistActivity
                var inputId = (activity as RegistActivity).reg_email_et
                var inputPwd = (activity as RegistActivity).reg_pwd_et
                var inputPwdCheck = (activity as RegistActivity).reg_pwd_check_et
                Log.d("TAG", "afterTextChanged: ${inputId.text} ${inputPwd.text} ${inputPwdCheck.text}")
                if (inputId.text.isNotEmpty() && inputPwd.text.isNotEmpty() && inputPwdCheck.text.isNotEmpty()) {
                    if (view.findViewById<EditText>(R.id.reg_guide_name_et).text.isNotEmpty()
                            //        && view.findViewById<Spinner>(R.id.country_guide_sp).isSelected
                            && view.findViewById<EditText>(R.id.reg_phone_et).text.isNotEmpty()
                            && view.findViewById<EditText>(R.id.reg_guide_info_et).text.isNotEmpty()
                            && view.findViewById<EditText>(R.id.reg_time_et).text.isNotEmpty()) {
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

        reg_guide_name_et = view.findViewById<EditText>(R.id.reg_guide_name_et)
        reg_guide_name_et.addTextChangedListener(textWatcher)

        reg_phone_et = view.findViewById<EditText>(R.id.reg_phone_et)
        reg_phone_et.addTextChangedListener(textWatcher)

        reg_guide_info_et = view.findViewById<EditText>(R.id.reg_guide_info_et)
        reg_guide_info_et.addTextChangedListener(textWatcher)

        reg_time_et = view.findViewById<EditText>(R.id.reg_time_et)
        reg_time_et.addTextChangedListener(textWatcher)

        val spinner = view.findViewById<Spinner>(R.id.reg_guide_country_select_sp)
        val countryList = CountryAdapter().countryList

        var arrayAdapter= ArrayAdapter(activity as RegistActivity, R.layout.support_simple_spinner_dropdown_item, countryList)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                UserDAO.userVO.country_cd = C.NationalCode.values()[position].country_cd
//                Log.d("cd", "${C.NationalCode.values()[position].country_cd}")
//                Log.d("uservo", "${UserDAO.userVO.country_cd}")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        return view
    }
//    fun country_gd(){
//
//        country_sp_g = reg_guide_country_select_sp
////        val country_sp_g = reg_guide_country_select_sp
//        val countryList = CountryAdapter().countryList
//        country_sp_g?.adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, countryList) }
////        country_sp_g.adapter = arrayAdapter
//
//        country_sp_g.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////                parent?.getItemAtPosition(position)
////                Log.d("nm", "${countryList[position]}")
//                UserDAO.userVO.country_cd = C.NationalCode.values()[position].country_cd
//                Log.d("코드", "${UserDAO.userVO.country_cd}")
//            }
//        }
//    }

    override fun onPause()  {
        super.onPause()
        reg_guide_name_et.removeTextChangedListener(textWatcher)
        reg_phone_et.removeTextChangedListener(textWatcher)
        reg_guide_info_et.removeTextChangedListener(textWatcher)
        reg_time_et.removeTextChangedListener(textWatcher)
    }
}
