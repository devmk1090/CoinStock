package com.devkproject.coinstock.helper

import com.devkproject.coinstock.model.Coin
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class Deserializer : JsonDeserializer<Coin> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Coin {
        val array = json?.asJsonArray
        return Coin(array?.get(0)?.asString ?: "Head", array?.get(1)?.asDouble ?: 0.0)
    }
}