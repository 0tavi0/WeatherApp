package com.example.otavioaugusto.testeyahooweather.interfaces

import android.content.Context

interface MainContrato {
    interface View{
        fun setLatLong(latitude:Double, longitude:Double)
        fun mensagem(msg:String)
        fun alert(msg: String)
        fun popularDados(cidade:String, icon:String, temp:Double, desc:String)


    }

    interface Presenter{
        fun getLocation(context: Context)
       // fun getLocationForNougat(context: Context)
        fun checkGPS(context: Context):Boolean
        fun getDadosAPI(lat:Double, long:Double)
        fun isNetworkConnected(context: Context):Boolean
        fun locationFused(context: Context)

    }
}