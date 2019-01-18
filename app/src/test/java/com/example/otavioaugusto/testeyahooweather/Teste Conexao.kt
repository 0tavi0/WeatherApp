package com.example.otavioaugusto.testeyahooweather

import android.content.Context
import com.example.otavioaugusto.testeyahooweather.presenter.MainPresenter
import com.example.otavioaugusto.testeyahooweather.view.MainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config


class `Teste Conexao` {

    val main: MainActivity = Mockito.mock(MainActivity::class.java)

    lateinit var context:Context

    @Test
    fun checkGPS(){

        var obj = MainPresenter(main)
        var result = obj.checkGPS(context)
        Assert.assertFalse(result)
    }




}