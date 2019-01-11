package com.example.otavioaugusto.testeyahooweather.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log

class Localizacao {


    companion object {
        @SuppressLint("MissingPermission")
        fun getLocation(context: Context){
            var locationManager:LocationManager
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            val provider = locationManager.getBestProvider(criteria, false)
            val location = locationManager.getLastKnownLocation(provider)


            if (location!=null){
                Log.e("Latitude${location.latitude}", "Longitude${location.longitude}")
            }else{
                Log.e("Local indisponivel", "Erro" + location)

            }
        }

    }
}