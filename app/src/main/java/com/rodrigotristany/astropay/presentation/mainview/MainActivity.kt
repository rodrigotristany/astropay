package com.rodrigotristany.astropay.presentation.mainview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rodrigotristany.astropay.R
import com.rodrigotristany.astropay.core.App

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as App)
            .appComponent
            .mainComponent()
            .create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}