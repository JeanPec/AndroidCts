package com.shindra.ctslibrary.apibo

import com.google.gson.annotations.SerializedName
import com.shindra.arrakis.extension.isValid
import com.shindra.arrakis.extension.toDate
import com.shindra.ctslibrary.mapper.T_DATE_FORMAT
import java.time.Duration
import java.util.*


data class Lines(@SerializedName("LinesDelivery") val linesDelivery: LinesDelivery) : CtsPersistentBo() {

}

data class LinesDelivery(
    @SerializedName("AnnotatedLineRef")
    val line: List<LineApi>,
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