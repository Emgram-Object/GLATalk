package com.example.glatalk_project.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.glatalk_project.model.UserModel
import com.example.glatalk_project.model.UserModelImpl
import com.example.glatalk_project.viewmodel.UserViewModel
import java.lang.IllegalArgumentException

class VMFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            UserModel::class.java -> UserViewModel(UserModelImpl()) as T
            else -> throw IllegalArgumentException("Unknown Class")
        }
    }
}