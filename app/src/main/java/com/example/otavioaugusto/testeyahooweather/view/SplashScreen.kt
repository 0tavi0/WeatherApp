package com.example.otavioaugusto.testeyahooweather.view

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.example.otavioaugusto.testeyahooweather.interfaces.ContratoSplash
import com.example.otavioaugusto.testeyahooweather.model.ResponseApi
import com.example.otavioaugusto.testeyahooweather.networking.ApiService
import com.example.otavioaugusto.testeyahooweather.networking.RetrofitService
import com.example.otavioaugusto.testeyahooweather.presenter.SplashPresenter
import com.example.otavioaugusto.testeyahooweather.utils.Localizacao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity(), ContratoSplash.View {


    val REQUEST_LOCATION = 1

    var lat: Double?=null
    var long: Double?=null
    lateinit var presenter: ContratoSplash.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SplashPresenter(this)

        checkPermission()

    }


    override fun setLatLong(latitude: Double, longitude: Double) {
        lat = latitude
        long = longitude
    }


    fun checkPermission(){
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
                REQUEST_LOCATION
            )

        else{

            presenter.getLocation(this)

//            Localizacao.getLocation(this)

            Handler().postDelayed({
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("latitude", lat)
                intent.putExtra("longitude", long)
                startActivity(intent)
            }, 2000)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode==REQUEST_LOCATION){
            presenter.getLocation(this)
//            Localizacao.getLocation(this)
            Handler().postDelayed({
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("latitude", lat)
                intent.putExtra("longitude", long)
                startActivity(intent)
            }, 2000)
        }else{

            finish()
        }
    }

    override fun setMessage(msg: String) {
        Toast.makeText( this, msg, Toast.LENGTH_LONG).show()
    }


//
// fun alert(){
//
//     val dialog = AlertDialog.Builder(this)
//     dialog.setMessage("Ops..Localização desativada")
//     dialog.setPositiveButton("Ativar?"){dialog, which ->
//         val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//         startActivityForResult(myIntent,2)
//     }
//
//     dialog.setNegativeButton("cancel"){dialog, which ->
//
//     }
//
//     dialog.show()
// }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2){
          //  presenter.getLocation(this)
        }else{
            Log.e("Erroactivity", "${requestCode}")

        }
    }


}
