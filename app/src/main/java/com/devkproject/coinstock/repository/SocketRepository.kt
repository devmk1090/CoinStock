package com.devkproject.coinstock.repository

import com.devkproject.coinstock.data.CoinService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketRepository @Inject constructor(
    private val coinService: CoinService
) {
    fun refreshCoin(coroutineScope: CoroutineScope) {
        coinService.getCoin()
            .launchIn(coroutineScope)
    }
}