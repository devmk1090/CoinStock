package com.devkproject.coinstock.data

import com.devkproject.coinstock.helper.Socket
import com.devkproject.coinstock.model.Upbit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinService @Inject constructor(
    private val socket: Socket,
    private val gson: Gson
) {

    companion object {
        const val URL = "wss://api.upbit.com/websocket/v1"
    }

    fun getCoin(): Flow<List<Upbit>> {
        return socket.connect(URL)
            .map {
                gson.fromJson(it, object : TypeToken<List<Upbit>>() {}.type) as List<Upbit>
            }
            .flowOn(Dispatchers.IO)
    }
}