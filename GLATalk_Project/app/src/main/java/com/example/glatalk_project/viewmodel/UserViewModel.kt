package com.example.glatalk_project.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.glatalk_project.base.BaseViewModel
import com.example.glatalk_project.model.UserModel
import com.example.glatalk_project.network.data.request.UserRequest

class UserViewModel (private val model: UserModel):BaseViewModel(){

    val userRegist = MutableLiveData<Boolean>(false)
//pro 뭐임??
    fun add(userRequest:UserRequest){
    addDisposalble(pro)
    }
}