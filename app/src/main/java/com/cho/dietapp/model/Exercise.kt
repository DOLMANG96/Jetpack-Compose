package com.cho.dietapp.model

data class Exercise(
    val docId : String = "",  //id를 기준으로 처리하기 위함.
    val name: String = "",
    val duration: Int = 0,
    val calorie : Int = 0
)