package com.android.copycreativeroutines.data

import java.io.Serializable

data class User(
    val id : String,
    val name : String,
    val point : Int
){
    data class Diary(
        val date:String?=null,
        val content:String?=null
    ):Serializable
}
