package com.example.glatalk_project.Controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.glatalk_project.R
import kotlinx.android.synthetic.main.activity_regist.*


class RegistActivity:AppCompatActivity() {

    lateinit var guideFragment: registGuideFragment
    lateinit var touristFragment: registTouristFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)

        touristFragment = registTouristFragment()
        guideFragment = registGuideFragment()

        reg_tour_radio_bnt.setOnClickListener(View.OnClickListener {
            reg_guide_radio_bnt.isChecked = false
            changeTouristFragment()
        })
        reg_guide_radio_bnt.setOnClickListener(View.OnClickListener {
            reg_tour_radio_bnt.isChecked = false
            changeGuideFragement()
        } )
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