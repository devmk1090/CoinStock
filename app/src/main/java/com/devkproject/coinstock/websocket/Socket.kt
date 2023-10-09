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
        private val TAG = Socket::class.java.simpleName
    }

    fun connect(url: String) = callbackFlow {
        val request = Request.Builder().url(url).build()

        val webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "Connected: $response")
            }

            //바이트 메세지 수신
            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                trySend(bytes.hex())
                Log.d(TAG, "onMessage: ${bytes.hex()}")
            }

            //WebSocket이 닫힌 경우
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                if (code != 1000) close(SocketNetworkException("Network Failure"))
                Log.d(TAG, "onClosed: $code")
            }

            //연결 실패 or 오류 발생
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                close(SocketNetworkException("onFailure"))
                Log.d(TAG, "onFailure")
            }
        })

        //Flow가 닫힐 때 WebSocket 연결도 Close
        awaitClose { webSocket.close(1000, "Closed") }
    }
        .retryWhen { cause, attempt ->
            if (attempt > 1) delay(1000 * attempt)
            else if (attempt >= 8) delay(8000)

            Log.d(TAG, "Retrying $attempt")
            cause is SocketNetworkException
        }

    fun send(upbit: Upbit) {
        val ticket = Upbit.Ticket("TEST_5")
        val format = Upbit.Format("DEFAULT")

    }

    class SocketNetworkException(message: String): Exception(message)
}