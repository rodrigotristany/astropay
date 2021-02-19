package com.rodrigotristany.astropay.presentation.mainview

import com.rodrigotristany.astropay.core.di.scopes.ActivityScope
import com.rodrigotristany.astropay.presentation.detailview.DetailFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: DetailFragment)
}