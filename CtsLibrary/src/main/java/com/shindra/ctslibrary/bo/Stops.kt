package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName
import com.shindra.ctslibrary.bo.CtsPersistentBo
import com.shindra.ctslibrary.bo.LineExtension


data class Stops(@SerializedName("StopPointsDelivery")
                      val stopPointsDelivery: StopPointsDelivery
) : CtsPersistentBo()

data class StopPointsDelivery(
    @SerializedName("AnnotatedStopPointRef")
    val annotatedStopPointRef: List<AnnotatedStopPointRef>,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String
)

data class AnnotatedStopPointRef(
    @SerializedName("Extension")
    val extension: Extension,
    @SerializedName("Lines")
    val lines: List<Line>,
    @SerializedName("Location")
    val location: Location,
    @SerializedName("StopName")
    val stopName: String,
    @SerializedName("StopPointRef")
    val stopPointRef: String
)

data class Extension(
    @SerializedName("LogicalStopCode")
    val logicalStopCode: String,
    @SerializedName("StopCode")
    val stopCode: String,
    val distance: Int
)



data class Location(
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)

data class Destination(
    @SerializedName("DirectionRef")
    val directionRef: Int,
    @SerializedName("DestinationName")
    val destinationName: List<String>
)