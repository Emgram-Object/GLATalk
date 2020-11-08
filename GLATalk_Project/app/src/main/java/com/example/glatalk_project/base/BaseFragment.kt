package com.example.glatalk_project.base;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer

abstract class BaseFragment<T:ViewDataBinding,U:BaseViewModel>:Fragment(){

    fun newInstance(fragment: Fragment, bundle: Bundle): Fragment {
        fragment.arguments = bundle
        return fragment
    }

    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int
    abstract var viewModel: U


    abstract fun initView()
    abstract fun initDataBinding()
    abstract fun initListener()
    //abstract fun invalidate()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup,
            savedInstanceState: Bundle?
    ):View?{
        viewDataBinding = DataBindingUtil.inflate(inflater,layoutResourceId,container,false)
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initView()
        initDataBinding()
        initListener()
    }
}
