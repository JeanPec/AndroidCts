package com.shindra.ctslibrary.bo

import android.icu.number.IntegerWidth
import com.shindra.ctslibrary.apibo.Coordinate
import com.shindra.ctslibrary.apibo.RouteType
import java.util.*
import kotlin.collections.ArrayList

data class Line(
    val name: String,
    val routeType: RouteType,
    val stops: ArrayList<Stop>? = null



)

data class Stop(
    val name: String?,
    val estimatedArrivalTime: Date? = null,
    val estimatedDepartureTime : Date? = null,
    val direction: String? = null,
    val position: Coordinate? = null
)