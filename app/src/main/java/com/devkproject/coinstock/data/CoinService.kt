package com.devkproject.coinstock.data

import com.devkproject.coinstock.helper.Socket
import com.devkproject.coinstock.model.Coin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.round

@Singleton
class CoinService @Inject constructor(
    private val socket: Socket,
    private val gson: Gson
) {

    companion object {
        const val URL = "ws://"
    }

    fun getCoin(): Flow<List<Coin>> {
        return socket.connect(URL)
            .map {
                gson.fromJson(it, object : TypeToken<List<Coin>>() {}.type) as List<Coin>
            }
            .map {
                it.map { item ->
                    item.copy(price = round(item.price * 1000) / 1000)
                }
            }
            .flowOn(Dispatchers.IO)
    }
}