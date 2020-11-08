package com.example.glatalk_project.core.util

import android.view.View
import com.example.glatalk_project.base.BaseView

class OnSingleClickListenger(private val clickListenger: (View)-> Unit):View.OnClickListener{
    companion object{
        const val INTERVAL = 500L
    }

    override fun onClick(p0: View?) {
        if(BaseView.clickable){
            
        }
    }

}