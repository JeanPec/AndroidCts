package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName

data class Lines(
    @SerializedName("LinesDelivery")
    val lines: LinesDelivery
) : CtsPersistentObject(){

}

data class LinesDelivery(
    @SerializedName("Line")
    val line: List<Line>,
    @SerializedName("RequestMessageRef")
    val requestMessageRef: String,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String,
    @SerializedName("ShortestPossibleCycle")
    val shortestPossibleCycle: String,
    @SerializedName("ValidUntil")
    val validUntil: String
)

data class Line(
    @SerializedName("Extension")
    val extension: LineExtension,
    @SerializedName("LineName")
    val lineName: String,
    @SerializedName("LineRef")
    val lineRef: String
)

data class LineDestination(
    @SerializedName("DestinationName")
    val destinationName: List<String>,
    @SerializedName("DirectionRef")
    val directionRef: Int
)

