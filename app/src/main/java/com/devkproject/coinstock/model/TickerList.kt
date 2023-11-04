package com.devkproject.coinstock.model

data class TickerList(
    var market: String,
    var korean_name: String,
    var english_name: String,
    var market_warning: String?,
)

/**
{
    "market": "KRW-BTC",
    "korean_name": "비트코인",
    "english_name": "Bitcoin"
},
{
    "market": "KRW-ETH",
    "korean_name": "이더리움",
    "english_name": "Ethereum"
},
{
    "market": "BTC-ETH",
    "korean_name": "이더리움",
    "english_name": "Ethereum"
},
{
    "market": "BTC-XRP",
    "korean_name": "리플",
    "english_name": "Ripple"
},
*/
