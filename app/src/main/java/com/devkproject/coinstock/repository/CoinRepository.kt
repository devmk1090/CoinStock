package com.devkproject.coinstock.repository

import com.devkproject.coinstock.api.UpbitService
import com.devkproject.coinstock.websocket.CoinService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepository @Inject constructor(
    private val coinService: CoinService,
    private val upbitService: UpbitService
) {
    fun refreshCoin() {
        coinService.getCoin()
    }

    suspend fun getUpbitTickerList() = withContext(Dispatchers.IO) {
        upbitService.getTickerList()
    }
}