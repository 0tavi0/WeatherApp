package com.example.otavioaugusto.testeyahooweather.view

import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.provider.Settings
import android.support.v7.app.AlertDialog
import com.example.otavioaugusto.testeyahooweather.R
import com.example.otavioaugusto.testeyahooweather.model.ResponseApi
import com.example.otavioaugusto.testeyahooweather.networking.ApiService
import com.example.otavioaugusto.testeyahooweather.networking.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.StrictMode




class MainActivity : AppCompatActivity() {

    lateinit var locationManager:LocationManager
    val APPID = "455e80676579a4e170260d0b04808f11"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var intent = intent
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude",0.0)

        Log.e("main ${latitude}", "longitude" + longitude)







        // verificarGPSisEnable()


        button.setOnClickListener {
            var call = RetrofitService
                .retrofit.create(ApiService::class.java)
                .getData(latitude,longitude, APPID)

           call.enqueue(object : Callback<ResponseApi>{
               override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                   Log.e("Erro", ""+t.message)
               }

               override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                   Log.e("response"+response.code(), ""+response.body()!!.name)

                   if(response.isSuccessful){
                       Log.e("Sucesso", ""+response.body()!!.ListWeather)

                       val weather = response.body()!!.ListWeather
                       for (i in weather){
                           Log.e("for", ""+i.icon)
                           Log.e("for", ""+i.main)

                       }

                   }
               }

           })



        }



    }


    fun verificarGPSisEnable():Boolean{
        locationManager =  getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)

        var isEnable = locationManager.isProviderEnabled(provider)

        if (!isEnable) {
            val dialog = AlertDialog.Builder(this)
            dialog.setMessage("erro")
            dialog.setPositiveButton("Ok"){dialog, which ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(myIntent,2)
            }

            dialog.setNegativeButton("cancel"){dialog, which ->

            }

            dialog.show()

            return false
        }

        return true
    }


}


