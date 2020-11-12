package com.shindra.ctslibrary.mapper

import com.shindra.arrakis.extension.toArrayList
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Stop
import com.shindra.ctslibrary.apibo.StopsApi
import io.reactivex.rxjava3.functions.Function

class DiscoverLineMapper(private val routeTypeFilter: RouteType, private val routeName: String) : Function<StopsApi, Line> {

    override fun apply(t: StopsApi?): Line {

        val lineByRouteTpe = t?.stopPointsDelivery?.annotatedStopPointRef?.filter { stopPointRef ->
            stopPointRef.lines?.any { it.lineExtension?.routeType == routeTypeFilter && it.lineRef == routeName }
                ?: false
        }
        val stops = lineByRouteTpe?.map {
            Stop(it.stopName,
                direction = it.lines?.first { it.lineRef == routeName }?.destinations?.first()?.destinationName?.first(),
                position = it.location)

        }.toArrayList()

        return Line(routeName, routeTypeFilter, stops)
    }
}

