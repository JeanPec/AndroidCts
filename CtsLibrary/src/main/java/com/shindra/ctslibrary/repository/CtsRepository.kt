package com.shindra.ctslibrary.repository

import com.google.gson.reflect.TypeToken
import com.shindra.arrakis.repository.Repository
import com.shindra.ctslibrary.bo.*
import com.shindra.ctslibrary.webservice.CtsService
import io.reactivex.rxjava3.core.Observable

private const val VELO_PARCS_STREAM_ID = "VELO_PARCS_STREAM_ID"
private const val STOP_POINTS_STREAM_ID = "STOP_POINTS_STREAM_ID"
private const val LINE_STREAM_ID = "LINE_STREAM_ID"
private const val ESTIMATED_TIME_TABLE = "ESTIMATED_TIME_TABLE"

class CtsRepository : Repository() {

    fun veloParc(): Observable<VeloParcs> {
        return withPersistentCache(VELO_PARCS_STREAM_ID, CtsService.veloParc(), object : TypeToken<VeloParcs>() {}.type).toObservable()
    }

    fun stopPoints(latitude: Double, longitude: Double, distanceArroundInM: Int): Observable<Stops> {
        return withPersistentCache(STOP_POINTS_STREAM_ID, CtsService.stopPoints(latitude, longitude, distanceArroundInM), object : TypeToken<Stops>() {}.type).toObservable()
    }

    fun lines(): Observable<Lines> {
        return withPersistentCache(LINE_STREAM_ID, CtsService.linesDiscovery(), object : TypeToken<Lines>() {}.type).toObservable()
    }

    fun estimatedTimeTable(routeType: RouteType, lineRef: String, directionRef: Int): Observable<EstimatedTimeTable> {
        return withPersistentCache(ESTIMATED_TIME_TABLE, CtsService.estimatedTimeTable(routeType, lineRef, directionRef), object : TypeToken<EstimatedTimeTable>() {}.type).toObservable()
    }
}