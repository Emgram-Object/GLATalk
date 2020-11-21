package com.example.glatalk_project.Activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.glatalk_project.Adapter.CountryAdapter
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C
import kotlinx.android.synthetic.main.fragment_reg_guide.*
import kotlinx.android.synthetic.main.fragment_reg_tour.*

class registGuideFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        country_guide_sp.adapter = activity?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, countryList) }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reg_guide,null)
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

}