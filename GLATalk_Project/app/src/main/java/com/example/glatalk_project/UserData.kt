package com.example.glatalk_project

data class LoginData (
       var loginToken:String ="",
       var desc :String="",
       var resultCode:String=""
){
    init {
        this.loginToken = loginToken
        this.desc = desc
        this.resultCode = resultCode
    }


}