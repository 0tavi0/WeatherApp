package com.example.otavioaugusto.testeyahooweather

import android.content.Context
import com.example.otavioaugusto.testeyahooweather.presenter.MainPresenter
import com.example.otavioaugusto.testeyahooweather.view.MainActivity
import org.junit.Test
import org.mockito.Mockito

class `Teste Conexao` {

    val mainActivity: MainActivity = Mockito.mock(MainActivity::class.java)

    @Test
    fun checkGPS(){
        val obj = MainPresenter(mainActivity)
          var context:Context

           // obj.checkGPS(context)
    }


}