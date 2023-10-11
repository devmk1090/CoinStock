package com.devkproject.coinstock.data

import com.devkproject.coinstock.websocket.Socket
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