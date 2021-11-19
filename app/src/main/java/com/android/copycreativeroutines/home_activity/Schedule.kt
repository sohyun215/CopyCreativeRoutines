package com.android.copycreativeroutines.home_activity

import java.io.Serializable

data class Schedule(
    val gName:String,  //위인이름
    val title:String,
    val time:String,
   // 나중에 날짜 추가 val date:String
):Serializable
{
}