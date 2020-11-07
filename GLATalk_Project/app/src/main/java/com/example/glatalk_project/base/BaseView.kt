package com.example.glatalk_project.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseView<T: ViewDataBinding, U: BaseViewModel>: AppCompatActivity(){

    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int
    abstract val viewModel: U

    abstract fun initView()
    abstract fun initDataBinding()
    abstract fun initListener()
    //abstract fun 필요하면 함수들 여기다 구현()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)

        initView()
        initDataBinding()
        initListener()
        //init 구현할 함수들()
    }


}