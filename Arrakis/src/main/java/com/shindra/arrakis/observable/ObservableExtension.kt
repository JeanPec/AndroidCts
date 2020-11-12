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

fun <T> BehaviorSubject<T>.observe(listener: ObservableListener<T>): Disposable? {
    var dispose: Disposable? = null

    this
        .doOnSubscribe { listener.onLoading() }
        .subscribe(
            {
                listener.onSuccess(it)
            },
            {
                listener.onError(it)
            })

    return dispose
}

interface ObservableListener<T> {
    fun onLoading()
    fun onSuccess(data: T)
    fun onError(throwable: Throwable)
}

