package com.example.glatalk_project.core.adapter

import com.example.glatalk_project.constant.C

class CountryAdapter {
    val countryList:ArrayList<C.NationalCode> = arrayListOf(C.NationalCode.ko, C.NationalCode.en, C.NationalCode.zh, C.NationalCode.ja)
    private var onCountrySelectListener: OnCountrySelectListener? = null

    fun getItemCount(): Int = countryList.size

    fun setCountrySelectListener(listener: OnCountrySelectListener) {
        onCountrySelectListener = listener
    }
    interface OnCountrySelectListener {
        fun onCountrySelected(item: C.NationalCode)
    }

}