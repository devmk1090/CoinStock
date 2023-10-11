package com.devkproject.coinstock.repository

import com.devkproject.coinstock.data.CoinService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketRepository @Inject constructor(
    private val coinService: CoinService
) {
    fun refreshCoin() {
        coinService.getCoin()
    }
}