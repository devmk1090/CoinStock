package com.devkproject.coinstock.api

import retrofit2.Retrofit

var BASE_URL = "https://api.upbit.com/"

fun <T> provideApiService(retrofit: Retrofit, service: Class<T>): T =
    retrofit.create(service)