package com.example.otavioaugusto.testeyahooweather.presenter

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.otavioaugusto.testeyahooweather.interfaces.MainContrato
import com.example.otavioaugusto.testeyahooweather.model.ResponseApi
import com.example.otavioaugusto.testeyahooweather.networking.ApiService
import com.example.otavioaugusto.testeyahooweather.networking.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var view:MainContrato.View):MainContrato.Presenter {
    var hasGPS = false
    var locationGps: Location? = null
    lateinit var locationManager: LocationManager
    lateinit var imgIcon:String


//    @SuppressLint("MissingPermission")
//    override fun getLocationForNougat(context: Context) {
//        var locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
////        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//
//        if (hasGPS) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0F, object : LocationListener {
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
//        } else {
//            Log.e("else", "  : ")
//
//            view.alert("Ops..Localização Desativada")
//
//
//        }
//    }


    override fun getDadosAPI(lat: Double, long: Double) {
        val APPID = "455e80676579a4e170260d0b04808f11"
        var call = RetrofitService
            .retrofit.create(ApiService::class.java)
            .getData(lat!!, long!!, APPID)

        call.enqueue(object : Callback<ResponseApi> {
            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e("Erro", "" + t.message)
                view.mensagem(t.message!!)
            }

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                Log.e("response" + response.code(), "" + response.body()!!.name)

                if (response.isSuccessful) {

                    var cidade = response.body()!!.name
                    var temp = response.body()!!.main.temp

                    val weather = response.body()!!.ListWeather

                    for (i in weather) {

                        val cond = condTempo(i.description)
                        view.popularDados(cidade, i.icon, temp, cond)

                    }
                }
            }

        })
    }

    fun condTempo(desc:String):String {
        when (desc) {
            "scattered clouds" -> return "Parcialmente Nublado"
            "clear sky" -> return "Céu Limpo"
            "few clouds" -> return "Poucas Nuvens"
            "broken clouds" -> return "Nuvens Quebradas"
            "shower rain" -> return "Pancadas de Chuva"
            "rain" -> return "Chuva"
            "thunderstorm" -> return "Trovoada"
        }
        return desc
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(context: Context) {

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)
        val location = locationManager.getLastKnownLocation(provider)


        if (location != null) {
            view.setLatLong(location.latitude, location.longitude)
        } else {
            view.mensagem("Local Indisponivel - ${location}")

        }


    }


    override fun checkGPS(context: Context): Boolean {


        var locationManager: LocationManager
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)


    }

    override fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}



