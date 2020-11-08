package com.example.glatalk_project.view

import androidx.lifecycle.ViewModelProvider
import com.example.glatalk_project.R
import com.example.glatalk_project.base.BaseFragment
import com.example.glatalk_project.base.BaseView
import com.example.glatalk_project.core.constant.Con
import com.example.glatalk_project.core.custom.adapter.CountryAdapter
import com.example.glatalk_project.core.util.VMFactory
import com.example.glatalk_project.databinding.ActivityRegistBinding
import com.example.glatalk_project.viewmodel.UserViewModel

class RegisterActivity : BaseFragment<ActivityRegistBinding,UserViewModel>(){


    override val layoutResourceId: Int
        get() = R.layout.activity_regist

    override lateinit var viewModel: UserViewModel

    private lateinit var countryAdapter: CountryAdapter
    private var selectedCountry: Con.NationalCode? = null

    override fun initView(){
        viewModel = ViewModelProvider(this, VMFactory()).get(UserViewModel::class.java)


//        countryAdapter = CountryAdapter()
//        country_rv.adapter = countryAdapter
    }
    override fun initDataBinding(){

    }
    override fun initListener(){

    }


}