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
import android.os.Build
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
import android.widget.ImageView
import com.example.otavioaugusto.testeyahooweather.interfaces.MainContrato
import com.example.otavioaugusto.testeyahooweather.presenter.MainPresenter
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity(), MainContrato.View {


    lateinit var mAinPresenter: MainContrato.Presenter

    var lat: Double?=null
    var long: Double?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAinPresenter = MainPresenter(this)

        val gpsStatus = mAinPresenter.checkGPS(this)
        val netWorkingStatus = mAinPresenter.isNetworkConnected(this)

        if (!netWorkingStatus){
            alertInternet(getString(R.string.erroInternet))
        }
        if (!gpsStatus){
            alert(getString(R.string.erroLocalizacao))

        }else{

            mAinPresenter.getLocation(this)
            mAinPresenter.getDadosAPI(lat!!,long!!)

        }


    }


    override fun setLatLong(latitude: Double, longitude: Double) {
        lat = latitude
        long = longitude

    }

    override fun mensagem(msg: String) {
        if (msg.equals("Time out")){
            alertInternet(getString(R.string.erroInternet))
        }else{
            alert(msg)
        }
    }

    override fun alert(msg: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(msg)
        dialog.setPositiveButton(getString(R.string.btnAtivar)){ dialog, which ->
            val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(myIntent,2)
        }

        dialog.setNegativeButton(getString(R.string.btnCancel)){ dialog, which ->

        }

        dialog.show()
    }


        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2) {

            mAinPresenter.getLocation(this)

        }
    }


    fun alertInternet(msg: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(msg)
        dialog.setPositiveButton(getString(R.string.btnOk)){ dialog, which ->
//            val myIntent = Intent(Settings.ACTION_WIFI_SETTINGS)
//            startActivityForResult(myIntent,2)
        }


        dialog.show()
    }

    override fun popularDados(cidade:String, icon:String, temp:Double, desc:String) {
        val url = "http://openweathermap.org/img/w/${icon}.png"
        Picasso.get().load(url).into(imgWeatherCondition)
        val celsius = temp - 273.15

        txtCityName.text = cidade
        txtTemperature.text = celsius.toString()
        txtWeatherCondition.text = desc

    }


}


