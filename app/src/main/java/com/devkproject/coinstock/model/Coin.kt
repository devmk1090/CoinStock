package com.devkproject.coinstock.model

data class UpbitSocket(
    var type: String,
    var codes: List<String>,
) {
    data class Ticket(
        var ticket: String
    )

    data class Format(
        var format: String
    )
}
