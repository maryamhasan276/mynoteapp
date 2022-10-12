package com.tecra.cloverly

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy

class APP : Application() {

    private var sharedPreferences: WSharedPreferences? = null

    override fun onCreate() {
        super.onCreate()
        CookieHandler.setDefault(
            CookieManager(
                null,
                CookiePolicy.ACCEPT_ALL
            )
        )
        instance = this
        sharedPreferences = WSharedPreferences(WSharedPreferences.MAIN_FILENAME, this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        sDefSystemLanguage = newConfig.locale.language
    }

    fun getSharedPreferences(): WSharedPreferences {
        return sharedPreferences ?: WSharedPreferences(
            WSharedPreferences.MAIN_FILENAME,
            this
        )
    }

    companion object {
        @get:Synchronized
        var instance: APP? = null
        var sDefSystemLanguage: String? = null
    }
}