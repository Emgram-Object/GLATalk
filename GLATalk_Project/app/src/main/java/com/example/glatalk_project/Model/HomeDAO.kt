package com.example.glatalk_project.Model

import com.example.glatalk_project.Data.TokenData
import com.example.glatalk_project.network.ApiServer
import com.example.glatalk_project.network.data.response.BaseResponse
import com.example.glatalk_project.network.data.response.HomeResponse
import retrofit2.Callback

object HomeDAO {
    fun tourist_home(callback: Callback<HomeResponse>){
        ApiServer.network.tourist_home().enqueue(callback)
    }

    fun guide_home(callback: Callback<HomeResponse>){
        ApiServer.network.guide_home().enqueue(callback)
    }
}