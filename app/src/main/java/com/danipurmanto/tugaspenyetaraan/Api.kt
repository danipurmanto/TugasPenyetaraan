package com.danipurmanto.tugaspenyetaraan

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("dataset.json")
    fun getData () : Call <Responses>
}