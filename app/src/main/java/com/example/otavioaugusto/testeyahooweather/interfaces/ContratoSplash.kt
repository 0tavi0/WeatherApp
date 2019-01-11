package com.example.otavioaugusto.testeyahooweather.interfaces

import android.content.Context

interface ContratoSplash {

    interface View{
        fun setLatLong(latitude:Double, longitude:Double)
        fun setMessage(msg:String)
//        fun setGPScheck(status:Boolean)

    }

    interface Presenter{

        fun getLocation(context: Context)
        //fun checkGPS(context: Context):Boolean

    }
}