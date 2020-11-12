package com.shindra.ctslibrary.apibo

import com.google.gson.annotations.SerializedName


data class StopsApi(@SerializedName("StopPointsDelivery")
                    val stopPointsDelivery: StopPointsDelivery? = null
) : CtsPersistentBo()

data class StopPointsDelivery(
    @SerializedName("AnnotatedStopPointRef")
    val annotatedStopPointRef: List<AnnotatedStopPointRef>? = null,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String? = null
)

data class AnnotatedStopPointRef(
    @SerializedName("Extension")
    val extension: Extension? = null,
    @SerializedName("Lines")
    val lines: List<LineApi>? = null,
    @SerializedName("Location")
    val location: Coordinate? = null,
    @SerializedName("StopName")
    val stopName: String? = null,
    @SerializedName("StopPointRef")
    val stopPointRef: String? = null
)

data class Extension(
    @SerializedName("LogicalStopCode")
    val logicalStopCode: String? = null,
    @SerializedName("StopCode")
    val stopCode: String? = null,
    val distance: Int? = null
)


data class Coordinate(
    @SerializedName("Latitude")
    val latitude: Double? = null,
    @SerializedName("Longitude")
    val longitude: Double? = null
)

data class Destination(
    @SerializedName("DirectionRef")
    val directionRef: Int? = null,
    @SerializedName("DestinationName")
    val destinationName: List<String>? = null
)