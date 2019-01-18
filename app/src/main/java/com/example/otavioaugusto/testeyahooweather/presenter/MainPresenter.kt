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
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.widget.ImageView
import com.example.otavioaugusto.testeyahooweather.R
import com.example.otavioaugusto.testeyahooweather.interfaces.MainContrato
import com.example.otavioaugusto.testeyahooweather.model.ResponseApi
import com.example.otavioaugusto.testeyahooweather.networking.ApiService
import com.example.otavioaugusto.testeyahooweather.networking.RetrofitService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(var view:MainContrato.View):MainContrato.Presenter {

    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationManager:LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null

    override fun getDadosAPI(lat: Double, long: Double) {
        val APPID = "455e80676579a4e170260d0b04808f11"
        var call = RetrofitService
            .retrofit.create(ApiService::class.java)
            .getData(lat!!, long!!, APPID)

        call.enqueue(object : Callback<ResponseApi> {
            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                view.mensagem(t.message!!)
            }

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
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
            "broken clouds" -> return "Nublado"
            "shower rain" -> return "Pancadas de Chuva"
            "rain" -> return "Chuva"
            "thunderstorm" -> return "Trovoada"
        }
        return desc
    }

    @SuppressLint("MissingPermission")
    override fun getLocation(context: Context) {

//        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val criteria = Criteria()
//        val provider = locationManager.getBestProvider(criteria, false)
//        val location = locationManager.getLastKnownLocation(provider)
//
//Log.e("locationLocationManager",""+location.latitude)
//        if (location != null) {
//            view.setLatLong(location.latitude, location.longitude)
//        } else {
//            view.alert("Local Indisponivel - ${location}")
//
//        }


        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {

            if (hasGps) {
                Log.e("Escolhido", "hasGps")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 300, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationGps = location


                            view.setLatLong(locationGps!!.latitude, locationGps!!.longitude)

                            Log.e("CodeAndroidLocation", " GPS Latitude : " + locationGps!!.latitude)
                            Log.e("CodeAndroidLocation", " GPS Longitude : " + locationGps!!.longitude)
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null)
                    locationGps = localGpsLocation

            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            locationNetwork = location
                             view.setLatLong(locationNetwork!!.latitude, locationNetwork!!.longitude)
                                                    }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

                    }

                    override fun onProviderEnabled(provider: String?) {

                    }

                    override fun onProviderDisabled(provider: String?) {

                    }

                })

                val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (localNetworkLocation != null)
                    locationNetwork = localNetworkLocation
            }



        }else{
            view.mensagem(context.getString(R.string.txtSemLocalizacao))
        }
    }


    override fun checkGPS(context: Context): Boolean {


        var locationManager: LocationManager
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return true
        }
        // otherwise return false
        return false
    }




    override fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    @SuppressLint("MissingPermission")
    override fun locationFused(context: Context) {

       fusedLocationClient =  LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->

                if (location != null) {

                    view.setLatLong(location!!.latitude, location!!.longitude)

                }
                else{

                    view.mensagem(context.getString(R.string.msgSemLocalizacao))
                }


            }
            .addOnFailureListener { e ->
                e.printStackTrace()

                view.mensagem(e.message.toString())
            }



    }

}



