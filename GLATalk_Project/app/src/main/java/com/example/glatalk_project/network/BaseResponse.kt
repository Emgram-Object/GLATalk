package com.example.glatalk_project.network

import org.json.JSONObject

open class BaseResponse(
        var resultCode: Int = -1,
        var desc: String? = null,
        var body: Any? = null)
    : ServerResult {

    override fun isSuccess(): Boolean {
        return resultCode == 0
    }

    override fun resultCode(): String {
        return resultCode.toString()
    }

    override fun errorMessage(): String? {
        return desc
    }


}
// BaseNetwork의 interface를 상속받은 클래스 -> 오버라이딩
