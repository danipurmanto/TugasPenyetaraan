package com.danipurmanto.tugaspenyetaraan

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://tugaspenyetaraan1-default-rtdb.firebaseio.com/"
    val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiNih= retrofit.create(Api::class.java)
}