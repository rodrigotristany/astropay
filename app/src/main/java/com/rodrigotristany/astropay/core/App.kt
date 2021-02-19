package com.rodrigotristany.astropay.core

import android.app.Application
import com.rodrigotristany.astropay.core.di.components.ApplicationComponent
import com.rodrigotristany.astropay.core.di.components.DaggerApplicationComponent

class App : Application() {
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private  fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}