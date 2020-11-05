package com.shindra.ctslibrary.reporsitory

import StopPoints
import com.google.gson.reflect.TypeToken
import com.shindra.arrakis.Repository.Repository
import com.shindra.ctslibrary.bo.*
import com.shindra.ctslibrary.webservice.CtsService
import io.reactivex.rxjava3.core.Single

private const val VELO_PARCS_STREAM_ID = "VELO_PARCS_STREAM_ID"
private const val STOP_POINTS_STREAM_ID = "STOP_POINTS_STREAM_ID"
private const val LINE_STREAM_ID = "LINE_STREAM_ID"
private const val ESTIMATED_TIME_TABLE = "ESTIMATED_TIME_TABLE"

class CtsRepository : Repository() {

    fun veloParc() : Single<VeloParcs>{
        return withPersistentCache(VELO_PARCS_STREAM_ID, CtsService.veloParc(),object: TypeToken<VeloParcs>(){}.type)
    }

    fun stopPoints(latitude : Double, longitude : Double, distanceArroundInM : Int) : Single<StopPoints>{
        return withPersistentCache(STOP_POINTS_STREAM_ID, CtsService.stopPoints(latitude,longitude,distanceArroundInM),object: TypeToken<StopPoints>(){}.type)
    }

    fun lines() : Single<Lines>{
        return withPersistentCache(LINE_STREAM_ID, CtsService.linesDiscovery(),object: TypeToken<Lines>(){}.type)
    }

    fun estimatedTimeTable(routeType: RouteType, lineRef : String, directionRef : Int) : Single<EstimatedTimeTable>{
        return withPersistentCache(ESTIMATED_TIME_TABLE, CtsService.estimatedTimeTable(routeType,lineRef,directionRef),object: TypeToken<EstimatedTimeTable>(){}.type )
    }

}