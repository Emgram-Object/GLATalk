package com.example.glatalk_project.Activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.glatalk_project.Adapter.CountryAdapter
import com.example.glatalk_project.Model.UserDAO
import com.example.glatalk_project.R
import com.example.glatalk_project.constant.C

class registTouristFragment:Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reg_tour,null)
        val spinner = view.findViewById<Spinner>(R.id.reg_tourist_country_select_sp)
        val countryList = CountryAdapter().countryList

        var arrayAdapter= ArrayAdapter(activity as RegistActivity, R.layout.support_simple_spinner_dropdown_item, countryList)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                UserDAO.userVO.country_cd = C.NationalCode.values()[position].country_cd
                Log.d("cd", "${C.NationalCode.values()[position].country_cd}")
                Log.d("uservo", "${UserDAO.userVO.country_cd}")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        return view
    }
}