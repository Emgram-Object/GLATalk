package com.example.glatalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.glatalk_project.R


class RegistActivity:AppCompatActivity() {

    lateinit var guideFragment: registGuideFragment
    lateinit var touristFragment: registTouristFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        changeUser()
    }
    inner class changeUser:View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.reg_tour_radio_bnt -> changeTouristFragment()
                R.id.reg_guide_radio_bnt -> changeGuideFragement()
            }
        }
    }

    fun changeTouristFragment(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl,touristFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

    }
    fun changeGuideFragement(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.reg_info_fl,guideFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
}