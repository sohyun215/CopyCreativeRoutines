package com.android.copycreativeroutines.data

data class Great(
    val name: String,
    val category: String,
    val image: String,
    val descript: String,
    val schedule: List<Schedule>
) {
    data class Schedule(
        val title: String,
        val start: String,
        val end: String
    )
}
