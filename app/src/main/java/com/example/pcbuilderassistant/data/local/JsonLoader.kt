package com.example.pcbuilderassistant.data.local

import android.content.Context
import com.example.pcbuilderassistant.data.local.model.HardwareJson
import com.google.gson.Gson
import java.io.InputStreamReader

object JsonLoader {

    fun loadHardware(context: Context): HardwareJson {

        val inputStream = context.assets.open("hardware.json")
        val reader = InputStreamReader(inputStream)

        return Gson().fromJson(reader, HardwareJson::class.java)
    }
}