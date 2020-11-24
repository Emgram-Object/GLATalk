package com.example.glatalk_project.util

import java.util.regex.Pattern

object TextUtil {

    fun idVerify(id: String): Boolean {
        val idPolicy = "^[A-z|0-9]([A-z|0-9]*)(@)([A-z]*)(\\.)([A-z]*)$"
        val pattern = Pattern.compile(idPolicy)
        val matcher = pattern.matcher(id)
        return matcher.matches()
    }

    fun pwdVerify(pwd: String): Boolean {
        val passwordPolicy = "^(?=.*[A-Za-z])(?=.*[0-9]).{8,}$"
        val pattern = Pattern.compile(passwordPolicy)
        val matcher = pattern.matcher(pwd)
        return matcher.matches()
    }

}