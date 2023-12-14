package com.d121211102.Valorantagent

import android.app.Application
import com.d121211102.Valorantagent.data.AppContainer
import com.d121211102.Valorantagent.data.DefaultAppContainer

class Valorantagent: Application() {
        lateinit var container: AppContainer

        override fun onCreate() {
            super.onCreate()
            container = DefaultAppContainer()
        }
    }
