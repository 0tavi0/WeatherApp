package com.example.otavioaugusto.testeyahooweather.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.otavioaugusto.testeyahooweather.interfaces.ContratoSplash
import com.example.otavioaugusto.testeyahooweather.view.MainActivity

class SplashPresenter(var view:ContratoSplash.View):ContratoSplash.Presenter {


    private var hasGps = false
    private var locationGps: Location? = null


    @SuppressLint("MissingPermission")
    override fun getLocation(context: Context) {
        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)
        val location = locationManager.getLastKnownLocation(provider)

        if (location!=null){

            view.setLatLong(location.latitude, location.longitude)
        }else{
            view.setMessage("Sem localização")

        }
    }


}