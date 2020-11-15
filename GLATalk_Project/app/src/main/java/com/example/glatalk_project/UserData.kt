package com.example.glatalk_project

data class UserData (
       var desc :String="",
       var resultCode:String=""
){
    init {
        this.desc = desc
        this.resultCode = resultCode
    }


}