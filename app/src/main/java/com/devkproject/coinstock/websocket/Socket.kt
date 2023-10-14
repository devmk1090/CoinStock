package com.devkproject.coinstock.websocket

import android.util.Log
import com.devkproject.coinstock.model.Ticker
import com.devkproject.coinstock.model.Upbit
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.retryWhen
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Socket @Inject constructor(private val client: OkHttpClient, private val gson: Gson) {

    companion object {
        private val TAG = "501501"
    }

    fun connect(url: String) {
        val request = Request.Builder().url(url).build()
        val webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "Connected: $response")
                val type = Upbit("ticker", arrayListOf("KRW-BTC", "KRW-ETH"))
                val ticket = Upbit.Ticket("test example")
                val format = Upbit.Format("DEFAULT")
                val test = gson.toJson(arrayListOf(ticket, type, format))

                webSocket.send(test)
            }

            //바이트 메세지 수신
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d(TAG, "onMessage: ${bytes.utf8()}")
                try {
                    val message = gson.fromJson(bytes.utf8(), Ticker::class.java)
                    Log.d(TAG, "code: ${message.code}")
                    Log.d(TAG, "high_price: ${message.high_price}")
                    Log.d(TAG, "low_price: ${message.low_price}")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


            //WebSocket이 닫힌 경우
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, "onClosed: $code")
            }

            //연결 실패 or 오류 발생
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.d(TAG, "onFailure")
            }
        })
    }
}