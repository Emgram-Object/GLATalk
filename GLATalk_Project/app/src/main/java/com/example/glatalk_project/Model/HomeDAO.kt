package com.example.glatalk_project.Model

import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.BaseResponse
import retrofit2.Callback

object HomeDAO {
    fun tourist_home(callback: Callback<BaseResponse>){
        ApiServer.network.tourist_home().enqueue(callback)
    }

    fun guide_home(callback: Callback<BaseResponse>){
        ApiServer.network.guide_home().enqueue(callback)
    }
}