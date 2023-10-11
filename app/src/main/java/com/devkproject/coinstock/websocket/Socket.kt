package com.devkproject.coinstock.websocket

import android.util.Log
import com.devkproject.coinstock.model.Upbit
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
class Socket @Inject constructor(private val client: OkHttpClient) {

    companion object {
        private val TAG = "501501"
    }

    fun connect(url: String) {
        val request = Request.Builder().url(url).build()
        val webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "Connected: $response")
                val requestJson = """
                    [
                      {
                        "ticket": "test example"
                      },
                      {
                        "type": "ticker",
                        "codes": [
                          "KRW-BTC",
                          "KRW-ETH"
                        ]
                      },
                      {
                        "format": "DEFAULT"
                      }
                    ]
                """.trimIndent()
                Log.d(TAG, "json : $requestJson")
                webSocket.send(requestJson)
            }

            //바이트 메세지 수신
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d(TAG, "onMessage: $bytes")
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