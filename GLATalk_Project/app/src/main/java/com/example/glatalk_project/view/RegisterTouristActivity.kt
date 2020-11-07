package com.example.glatalk_project.view

import com.example.glatalk_project.R
import com.example.glatalk_project.base.BaseView
import com.example.glatalk_project.databinding.ActivityRegistBinding
import com.example.glatalk_project.viewmodel.UserViewModel

class RegisterTouristActivity : BaseView<ActivityRegistBinding,UserViewModel>(){

    override val layoutResourceId: Int

        get() = R.layout.activity_regist
    override lateinit var viewModel: UserViewModel
}