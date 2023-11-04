package com.devkproject.coinstock.websocket

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinService @Inject constructor(
    private val socket: Socket,
) {

    companion object {
        const val URL = "wss://api.upbit.com/websocket/v1"
    }

    fun getCoin() {
        socket.connect(URL)
    }
}