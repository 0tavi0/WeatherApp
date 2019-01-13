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
    var hasNetwork = false
    var statusGps = false
    private var locationGps: Location? = null



    override fun checkGPS(context: Context) : Boolean{


        var locationManager:LocationManager
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        Log.e("checkGPS",""+ locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        )


        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)


    }


    @SuppressLint("MissingPermission")
    override fun getLocation(context: Context) {
        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)
        val location = locationManager.getLastKnownLocation(provider)

        if (provider == LocationManager.GPS_PROVIDER){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object : LocationListener{
                override fun onLocationChanged(location: Location?) {
                    view.setLatLong(location!!.latitude, location!!.longitude)

                    Log.e("locationviewlat",""+location!!.latitude)
                    Log.e("locationviewlongi",""+location!!.longitude)
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    view.setMessage(provider.toString())
                }

                override fun onProviderEnabled(provider: String?) {
                    view.setMessage(provider.toString())
                }

                override fun onProviderDisabled(provider: String?) {

                    view.setMessage(provider.toString())


                }

            })

        }

        Log.e("location",""+location)

        if (location==null){
            view.setMessage("Sem localização")

        }else{
            view.setLatLong(location.latitude, location.longitude)
            Log.e("locationviewlat",""+location.latitude)
            Log.e("locationviewlongi",""+location.longitude)



        }


    }


}