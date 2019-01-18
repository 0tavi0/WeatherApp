package com.example.otavioaugusto.testeyahooweather.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
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
import java.security.Provider

class SplashScreen : AppCompatActivity(), ContratoSplash.View {


    val REQUEST = 1
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000
    private var permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    internal val mRunnable: Runnable = Runnable {
    if (!isFinishing) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissions)) {

                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
            } else {
                requestPermissions(permissions, REQUEST)
            }
        } else {
            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        }



    }

    private fun checkPermission(permissionArray: Array<String>): Boolean {

        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    val requestAgain = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(permissions[i])
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Go to settings and enable the permission", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            if (allSuccess)

                mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)


        }
    }



//    override fun setLatLong(latitude: Double, longitude: Double) {
//        lat = latitude
//        long = longitude
//
//    }
//
//    fun checkPermission(){
//        if (ActivityCompat.checkSelfPermission(
//                this, android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        )
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf<String>(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),
//                REQUEST_LOCATION
//            )
//
//        else{
//
//            Handler().postDelayed({
//                var intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            }, 2000)
//
//        }
//    }


}


