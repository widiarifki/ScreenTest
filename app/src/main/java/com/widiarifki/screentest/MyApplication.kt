package com.widiarifki.screentest

import android.app.Application

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by widiarifki on 13/03/2018.
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize & Configure Realm
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
                .name("realmExample.realm")
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }
}
