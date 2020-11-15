//package com.example.glatalk_project.Model
//
//import com.example.glatalk_project.network.ApiServer
//import com.example.glatalk_project.network.BaseResponse
//import com.example.glatalk_project.network.data.request.LangRequest
//import com.example.glatalk_project.network.data.request.ProfileRequest
//import com.example.glatalk_project.network.data.response.ProfileResponse
//import com.example.glatalk_project.network.networkInterface
//import io.reactivex.Single
//
//class MyModel {
//    fun detail_info(): Single<ProfileResponse>
//    fun info_modify(profileRequest: ProfileRequest()): Single<BaseResponse>
//
//}
//
//
//class MyModelImpl: MyModel {
//    override fun detail_info(): Single<ProfileResponse> {
//        return ApiServer.API.detail_info().toSingle()
//    }
//
//    override fun info_modify(profileRequest: ProfileRequest): Single<BaseResponse> {
//        return ApiServer.API.info_modify(
//                profileRequest.profile_img,
//                profileRequest.user.user_name,
//                profileRequest.user.user_phone_num,
//                profileRequest.user.country_cd,
//                profileRequest.user.country_phone_code).toSingle()
//    }
//
//    override fun change_pwd(pwdRequest: PwdRequest): Single<BaseResponse> {
//        return ApiServer.API.change_pwd(pwdRequest).toSingle()
//    }
//
//    override fun logout(): Single<BaseResponse> {
//        return ApiServer.API.logout().toSingle()
//    }
//
//    override fun user_language(langRequest: LangRequest): Single<BaseResponse> {
//        return ApiServer.API.user_language(langRequest).toSingle()
//    }
//}