package com.shindra

import androidx.lifecycle.ViewModel
import com.shindra.arrakis.observable.convertToBehaviorSubject
import com.shindra.arrakis.observable.subscribeOnAndObserveOn
import com.shindra.ctslibrary.bo.*
import com.shindra.ctslibrary.repository.CtsRepository
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MyViewModel : ViewModel() {

    private val ctsRepository = CtsRepository()

    fun veloParcs(): BehaviorSubject<VeloParcs> {
        return ctsRepository
            .veloParc()
            .subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

    fun stopPoints(latitude: Double, longitude: Double, searchAroundInM: Int): BehaviorSubject<Stops> {
        return ctsRepository
            .stopPoints(latitude, longitude, searchAroundInM)
            .subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

    fun linesDelivery(): BehaviorSubject<Lines> {
        return ctsRepository
            .lines()
            .subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

    fun estimatedTimeTable(routeType: RouteType, lineRef: String, direction: Int): BehaviorSubject<EstimatedTimeTable> {
        return ctsRepository
            .estimatedTimeTable(routeType, lineRef, direction)
            .subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

}


