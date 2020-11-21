package com.shindra.ctslibrary.mapper

import com.shindra.arrakis.extension.toArrayList
import com.shindra.arrakis.extension.toDate
import com.shindra.ctslibrary.apibo.EstimatedTimeTable
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Stop
import io.reactivex.rxjava3.functions.Function

const val T_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

class LineStopEstimatedTimeMapper(private val routeTypeFilter: RouteType, private val routeName: String) : Function<EstimatedTimeTable, Line> {

    override fun apply(t: EstimatedTimeTable?): Line {
        val stops = t?.serviceDelivery
                ?.estimatedTimetableDelivery?.first()
                ?.estimatedJourneyVersionFrame?.first()
                ?.estimatedVehicleJourney?.first()
                ?.estimatedCalls?.map {
                    Stop(it.stopPointName, it.expectedArrivalTime?.toDate(T_DATE_FORMAT), it.expectedDepartureTime?.toDate(T_DATE_FORMAT), it.destinationName)
                }.toArrayList()

        return Line(routeName, routeTypeFilter, stops)

    }
}


