package com.shindra.arrakis.repository

import android.util.Log
import com.google.gson.Gson
import com.shindra.arrakis.ArrakisApplication
import com.shindra.arrakis.bo.Persistant
import com.shindra.arrakis.bo.PersistentCacheWrapper
import com.shindra.arrakis.cache.Cache
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type

open class Repository {

    fun <T> withCache(key: String, networkObservable: Single<T>, cache: Cache = Cache()): Single<T> {

        val on = networkObservable.map {
            Log.d("Repository", "key : $key is put in cache with result : ${it.toString()}")
            cache.put(key, it)
            it
        }

        val oc = Maybe.create<T> { emitter ->
            val t = cache.get<T>(key)
            Log.d("Repository", "key : $key is loaded from cache ")
            t?.let { emitter.onSuccess(it) } ?: emitter.onComplete()
        }

        return oc.switchIfEmpty(on)

    }

    fun <T : Persistant> withPersistentCache(key: String, networkObservable: Single<T>, type: Type): Single<T> {
        val dao = ArrakisApplication.persistentDataBase.getPersistentWrapperDao()

        val sN = networkObservable.map {
            Log.d("Repository", "key : $key is put in persistent cache")
            dao.insert(PersistentCacheWrapper(key,it.toJson()))
            it
        }

        val pM = dao.getPersistentWrapper(key).flatMap {
            Log.d("Repository", "key : $key is loaded from persistent cache")

            val t = Gson().fromJson<T>(it.dataAsJson,type)

            if(!t.isValid(it.dateOfInsertion)) {
                Log.d("Repository", "key : $key loaded from persistent cache is no longer valid switchin to network call")
                dao.delete(it)
               sN.toMaybe()
            }else{
                Maybe.just(t)
            }
        }

        return pM.switchIfEmpty(sN)

    }

}