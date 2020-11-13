package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName
import com.shindra.arrakis.extension.isValid
import java.time.Duration
import java.util.*


data class Lines(@SerializedName("LinesDelivery") val linesDelivery: LinesDelivery) : CtsPersistentBo() {
    override fun isValid(dateOfInsertion: Date) =
        dateOfInsertion.isValid(Duration.parse(linesDelivery.shortestPossibleCycle))
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

data class LineDestination(
    @SerializedName("DestinationName")
    val destinationName: List<String>,
    @SerializedName("DirectionRef")
    val directionRef: Int
)