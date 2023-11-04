package com.devkproject.coinstock.api

import com.devkproject.coinstock.model.TickerList
import retrofit2.http.GET

interface UpbitService {

    @GET("v1/market/all")
    suspend fun getTickerList(): List<TickerList>
}