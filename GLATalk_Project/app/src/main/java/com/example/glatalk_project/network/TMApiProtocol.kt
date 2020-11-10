package com.example.glatalk_project.network

import com.example.glatalk_project.network.data.request.LangRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.PUT

interface TMApiProtocol {
    /**
     * 언어설정
     */
    @PUT("api/v1/common/user_language")
    fun user_language(@Body langRequest: LangRequest):Call<BaseResponse>
    /**
     * 로그인
     */

    /**
     * 로그아웃
     */

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
