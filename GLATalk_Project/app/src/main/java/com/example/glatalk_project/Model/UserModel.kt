package com.example.glatalk_project.Model

import com.example.glatalk_project.network.BaseResponse
import com.example.glatalk_project.network.MainApiServer
import com.example.glatalk_project.network.data.request.LangRequest
import com.example.glatalk_project.network.data.request.UserRequest
import io.reactivex.Single

interface UserModel {
    fun userLanguage(request: LangRequest):Single<BaseResponse>
}
class UserModelImpl:UserModel{
    override fun userLanguage(request: LangRequest): Single<BaseResponse> {
        return MainApiServer.API.user_language(request)
    }
}