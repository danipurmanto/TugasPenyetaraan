package com.danipurmanto.tugaspenyetaraan


data class Responses(
    val data: Data,
    val status: String
)

data class Data(
    val co2: Int,
    val date: String,
    val humidity: Int,
    val ph: Int,
    val tds: Int,
    val wind: Int,
    val temp: Int
)

