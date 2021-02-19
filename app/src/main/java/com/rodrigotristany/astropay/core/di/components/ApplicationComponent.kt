package com.rodrigotristany.astropay.core.di.components

import android.content.Context
import com.rodrigotristany.astropay.core.di.modules.DataModule
import com.rodrigotristany.astropay.core.di.modules.DomainModule
import com.rodrigotristany.astropay.presentation.mainview.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DomainModule::class,
    DataModule::class])
interface ApplicationComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}