package com.example.glatalk_project.network.data.response

import com.example.glatalk_project.network.ServerResult

open class BaseArrayResponse(
        var resultCode: Int = -1,
        var desc: String? = null
): ServerResult {

    override fun isSuccess(): Boolean {
        return resultCode == 0
    }

    override fun resultCode(): String {
        return resultCode().toString()
    }

    override fun errorMessage(): String? {
        return desc
    }
}