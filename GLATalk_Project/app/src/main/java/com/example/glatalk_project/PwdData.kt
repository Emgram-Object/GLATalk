package com.example.glatalk_project

data class PwdData(

        var desc: String = "",
        var resultCode: String = ""
) {
    init {
        this.desc = desc
        this.resultCode = resultCode
    }

}