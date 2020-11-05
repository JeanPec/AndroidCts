package com.shindra.arrakis.cache

import android.util.LruCache

private const val MAX_SIZE = 50
/*
interface Cache {

    fun getCache() : LruCache<String,Any>

}*/

open class Cache {

    val cache = LruCache<String, Any>(MAX_SIZE)

    fun <T> put(key: String, data: T) {
        cache.put(key, data)
    }


    fun <T> get(key: String): T? {
        return cache.get(key) as? T
    }

}