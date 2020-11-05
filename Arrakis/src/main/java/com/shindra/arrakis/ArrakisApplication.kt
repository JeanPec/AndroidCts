package com.shindra.arrakis

import android.app.Application
import android.content.Context
import com.shindra.arrakis.cache.PersistentRoom
import java.lang.Appendable

open class ArrakisApplication : Application() {

    companion object {
        lateinit var arrakisContext: Context
        lateinit var persistentDataBase: PersistentRoom
    }

    override fun onCreate() {
        super.onCreate()
        arrakisContext = this
        persistentDataBase = PersistentRoom.buildRoomDataBase(arrakisContext)
    }
}