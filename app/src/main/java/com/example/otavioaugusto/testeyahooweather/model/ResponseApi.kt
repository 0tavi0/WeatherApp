package com.example.otavioaugusto.testeyahooweather.model

import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("weather")
    val ListWeather:List<Weather>,
    @SerializedName("name")
    val name:String

)
