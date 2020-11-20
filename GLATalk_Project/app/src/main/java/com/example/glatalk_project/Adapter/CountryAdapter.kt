package com.example.glatalk_project.Adapter

import com.example.glatalk_project.constant.C

class CountryAdapter {
    val countryList: ArrayList<String> = arrayListOf(C.NationalCode.ko.country_nm, C.NationalCode.en.country_nm, C.NationalCode.zh.country_nm, C.NationalCode.ja.country_nm)
    private var onCountrySelectListener: OnCountrySelectListener? = null

//    fun getItemCount(): Int = countryList.size

    fun setCountrySelectListener(listener: OnCountrySelectListener) {
        onCountrySelectListener = listener
    }
    interface OnCountrySelectListener {
        fun onCountrySelected(item: C.NationalCode)
    }
    val ko_p = C.NationalCode.ko.pos
    val en_p = C.NationalCode.en.pos
    val ja_p = C.NationalCode.ja.pos
    val zh_p = C.NationalCode.zh.pos


}