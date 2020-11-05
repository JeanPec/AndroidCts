package com.shindra.arrakis.bo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Entity()
data class PersistentCacheWrapper(@PrimaryKey val key : String,
                                  val dataAsJson : String,
                                  val dateOfInsertion : Date = Calendar.getInstance().time){

    inline fun <reified T> fromJson(): T {
        return Gson().fromJson(dataAsJson, object: TypeToken<T>(){}.type)
    }

    fun toJson(data : Any) : String{
        return Gson().toJson(data)
    }
}

