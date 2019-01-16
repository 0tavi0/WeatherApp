package com.example.otavioaugusto.testeyahooweather.presenter

import android.Manifest
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

    var hasGPS = false
    var locationGps: Location? = null



//    override fun checkGPS(context: Context) : Boolean{
//
//        var locationManager:LocationManager
//        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        Log.e("location",""+locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//
//    }

//    @SuppressLint("MissingPermission")
//    override fun getLocation(context: Context) {
//        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
//        if (hasGPS) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 8000, 0F, object : LocationListener {
//                override fun onLocationChanged(location: Location?) {
//                    if (location != null) {
//                        locationGps = location
//                        view.setLatLong(locationGps!!.latitude, locationGps!!.longitude)
//                        Log.e("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
//                        Log.e("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
//                    }
//                }
//
//                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//
//                }
//
//                override fun onProviderEnabled(provider: String?) {
//                   // view.setLatLong(locationGps!!.latitude, locationGps!!.longitude)
//
//                }
//
//                override fun onProviderDisabled(provider: String?) {
//
//                }
//
//            })
//
//            val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//            if (localGpsLocation != null)
//                locationGps = localGpsLocation
//        }
//    }

//        //atualiza a cada 5 segundos a posição do gps
//        if (provider == LocationManager.GPS_PROVIDER){
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, object : LocationListener{
//                override fun onLocationChanged(location: Location?) {
//                    view.setLatLong(location!!.latitude, location!!.longitude)
//                    Log.e("locationviewlat",""+location!!.latitude)
//                    Log.e("locationviewlongi",""+location!!.longitude)
//                }
//
//                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//                    Log.d("Provider", "${provider}")
//                }
//
//                override fun onProviderEnabled(provider: String?) {
//                    Log.d("Provider", "${provider}")
//                }
//
//                override fun onProviderDisabled(provider: String?) {
//
//                    Log.d("Provider", "${provider}")
//
//
//                }
//
//            })
//
//        }
//
//        if (location==null){
//            view.setMessage("Sem localização")
//
//        else{
//            view.setLatLong(location.latitude, location.longitude)
//            Log.e("locationviewlat",""+location.latitude)
//            Log.e("locationviewlongi",""+location.longitude)
//
//        }

}