package com.example.glatalk_project.network

import android.app.DownloadManager
import com.example.glatalk_project.network.data.request.LangRequest
import com.example.glatalk_project.network.data.request.LoginRequest
import com.example.glatalk_project.network.data.response.LoginResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface networkInterface {
    /**
     * 언어설정
     */
    @PUT("api/v1/common/user_language")
    fun user_language(@Body langRequest: LangRequest):Call<BaseResponse>


    /**
     * 로그인
     */
    @POST("api/v1/user/login")
    fun login(@Body loginRequest: LoginRequest):Call<LoginResponse>


    /**
     * 로그아웃 ..로그아웃은 프로토콜이 없나..?
//     */
//    @GET("/v1/user/logout")
//    fun logout(@Body request: LangRequest  )


    /**
     * 회원가입
     */



    /**
     * 비밀번호찾기
     */



    /**
     * 내정보 조회
     */



    /**
     * 내정보 변경
     */



    /**
     * 비밀번호 변경
     */



    /**
     * 프로필 이미지 삭제
     */



    /**
     * 관광객 홈
     */



    /**
     * 가이드 홈
     */



    /**
     * 대화 내역 목록
     */



    /**
     * 대화방 삭제
     */



    /**
     * 번역
     */

}
