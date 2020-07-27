package com.aldemir.retryretrofit.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface StatesServiceAPI {
    @Retry
    @GET("estados")
    fun getStates(): Call<ArrayList<StatesResponse>>
}