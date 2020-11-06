package com.shindra.arrakis.observable

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject


fun <T> Observable<T>.subscribeOnAndObserveOn(): Observable<T> {
    return this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.convertToBehaviorSubject(): BehaviorSubject<T> {
    val subject = BehaviorSubject.create<T>()
    this.subscribe(subject)
    return subject

}

fun <T> BehaviorSubject<T>.subscribeWithLifecycle(lifecycle: Lifecycle, listener: ObservableListener<T>) : Disposable? {
    var dispose: Disposable? = null

    this.doOnSubscribe { listener.onLoading() }

    lifecycle.addObserver(object : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun subscribe() {
            Log.d("Subscribe","OnSubscribe")
            this@subscribeWithLifecycle.subscribe(
                {
                    listener.onSuccess(it)
                },
                {
                    listener.onError(it)
                })
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun dispose() {
            Log.d("Subscribe","OnDispose")
            this@subscribeWithLifecycle.onComplete()
        }
    })

    return dispose
}

interface ObservableListener<T>{
    fun onLoading()
    fun onSuccess(data : T)
    fun onError(throwable: Throwable)
}

