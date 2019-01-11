package com.example.otavioaugusto.testeyahooweather.networking

import com.example.otavioaugusto.testeyahooweather.model.ResponseApi
import com.example.otavioaugusto.testeyahooweather.model.Weather
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

   // api.openweathermap.org/data/2.5/weather?lat=-5.0738973&lon=-42.7543107&APPID=89dc21c4775841aff319fe0d2aefc960
    @GET("/data/2.5/weather")
    fun getData(@Query("lat") lat:Double,
                @Query("lon") lon:Double,
                @Query("APPID") APPID: String) : Call<ResponseApi>
}