package com.shindra.utilities

import androidx.lifecycle.ViewModel
import com.shindra.arrakis.observable.convertToBehaviorSubject
import com.shindra.arrakis.observable.subscribeOnAndObserveOn
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.*
import com.shindra.ctslibrary.repository.CtsRepository
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MyViewModel : ViewModel() {

    private val ctsRepository = CtsRepository()

    fun lines(): BehaviorSubject<ArrayList<Line>> {
        return ctsRepository
            .lines()
            .subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

    fun lineWithStop(routeType: RouteType, routeName: String): BehaviorSubject<Line> {
        return ctsRepository.lineWithStops(routeType, routeName).subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

    fun lineWithEstimatedTimeTable(routeType: RouteType, lineRef: String, direction: Int): BehaviorSubject<Line> {
        return ctsRepository.lineWithEstimatedTime(routeType, lineRef, direction).subscribeOnAndObserveOn()
            .convertToBehaviorSubject()
    }

}


