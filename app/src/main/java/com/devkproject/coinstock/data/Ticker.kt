package com.devkproject.coinstock.data

data class Ticker(
    var type: String,
    var code: String,   //마켓 코드 (ex. KRW-BTC)
    var opening_price: Double, //시가
    var high_price: Double, //고가
    var low_price: Double, //저가
    var trade_price: Double, //현재가
    var prev_closing_price: Double, //전일 종가
    var change: String, //전일 대비
    var timestamp: Long,
)
