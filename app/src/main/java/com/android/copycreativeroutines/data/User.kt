package com.android.copycreativeroutines.data

data class User(
    val id : String,
    val point : Int = 0
){
    data class Diary(
        val date : String,
        val content : String
    )
    data class Schedule(
        val title: String,
        val date: String,
        val start: String,
        val end: String,
        val success: String
    )
}