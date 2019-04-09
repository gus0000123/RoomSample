package com.hyun.android.roomsample

import android.app.Application
import com.facebook.stetho.Stetho

class RoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}