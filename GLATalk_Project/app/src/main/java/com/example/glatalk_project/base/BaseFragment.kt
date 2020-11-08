package com.example.glatalk_project.base;

import androidx.databinding.ViewDataBinding

abstract class BaseFragment<T:ViewDataBinding,U:BaseViewModel>{
    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int
    abstract var viewModel: U


    abstract fun initView()
    abstract fun initDataBinding()
    abstract fun initListener()
    //abstract fun invalidate()
}
