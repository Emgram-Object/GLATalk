package com.example.glatalk_project.network

import com.example.glatalk_project.Data.ChatData
import com.example.glatalk_project.network.data.request.*
import com.example.glatalk_project.network.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface networkInterface {
    /**
     * 언어설정
     */
    @PUT("v1/common/user_language")
    fun user_language(@Body langRequest: LangRequest):Call<BaseResponse>


    /**
     * 로그인
     */
    @POST("v1/user/login")
    fun login(@Body loginRequest: LoginRequest):Call<BaseResponse>


    /**
     * 로그아웃
//     */
    @GET("v1/user/logout")
    fun logout(): Call<BaseResponse>


    /**
     * 회원가입
     */

    @POST("v1/user/add")
    fun add(@Body userRequest: UserRequest):Call<BaseResponse>



    /**
     * 비밀번호찾기
     */
    @POST("v1/user/pwd_find")
    fun pwd_find(@Body pwdFindRequest: PwdFindRequest):Call<BaseResponse>



    /**
     * 내정보 조회
     */
    @GET("v1/my/detail_info")

    fun detail_info(): Call<BaseResponse>




    /**
     * 내정보 변경
     */
//    @Multipart
    @POST("v1/my/modify_info")
//    fun modify_info(@Part("user_name") user_name: String,
//                    @Part("phone_number") phone_number: String,
//                    @Part("country_cd") country_cd: String?): Call<BaseResponse>
    fun modify_info(@Body profileRequest: ProfileRequest) : Call<BaseResponse>




    /**
     * 비밀번호 변경
     */
    @POST("v1/my/change_pwd")
    fun change_pwd(@Body pwdRequest: PwdRequest):Call<BaseResponse>


    /**
     * 프로필 이미지 삭제
     */



    /**
     * 관광객 홈
     */
    @GET("v1/home/tourist")
    fun tourist_home(): Call<HomeResponse>

    /**
     * 가이드 홈
     */
    @GET("v1/home/guide")
    fun guide_home() : Call<HomeResponse>


    /**
     * 대화 내역 목록
     */



    /**
     * 대화방 삭제
     */


    /**
     * 번역
     */
    @POST("v1/chat/translation")
    fun translation(@Body chatData: ChatData): Call<PapagoResonse>

    /**
     * 대화내역삭제
     */
    @DELETE("v1/chat/chat_delete")
    fun chat_delete(@Query("room_id") room_id: String):Call<BaseArrayResponse>

    /**
     * 대화내역리스트
     */
    @GET("v1/chat/chat_list")
    fun chat_list(@Query("room_id") room_id: String):Call<ChatResponse>
}
