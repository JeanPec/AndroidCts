package com.shindra.ctslibrary.apibo

import com.google.gson.annotations.SerializedName
import com.shindra.arrakis.extension.isValid
import com.shindra.arrakis.extension.toDate
import com.shindra.ctslibrary.mapper.T_DATE_FORMAT
import java.time.Duration
import java.util.*


data class EstimatedTimeTable(@SerializedName("ServiceDelivery")
                              val serviceDelivery: ServiceDelivery) : CtsPersistentBo() {

    override fun isValid(dateOfInsertion: Date): Boolean {
        val validDate = serviceDelivery.estimatedTimetableDelivery?.first()?.validUntil?.toDate(T_DATE_FORMAT)
        return Calendar.getInstance().time.time < (validDate?.time ?: 0)
    }
}

data class ServiceDelivery(
    @SerializedName("EstimatedTimetableDelivery")
    val estimatedTimetableDelivery: List<EstimatedTimetableDelivery>? = null,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String? = null
)

data class EstimatedTimetableDelivery(
    @SerializedName("EstimatedJourneyVersionFrame")
    val estimatedJourneyVersionFrame: List<EstimatedJourneyVersionFrame>? = null,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String? = null,
    @SerializedName("ShortestPossibleCycle")
    val shortestPossibleCycle: String? = null,
    @SerializedName("ValidUntil")
    val validUntil: String? = null,
    val version: String? = null
)

data class EstimatedJourneyVersionFrame(
    @SerializedName("EstimatedVehicleJourney")
    val estimatedVehicleJourney: List<EstimatedVehicleJourney>? = null,
    @SerializedName("RecordedAtTime")
    val recordedAtTime: String? = null
)

data class EstimatedVehicleJourney(
    @SerializedName("DirectionRef")
    val directionRef: Int? = null,
    @SerializedName("EstimatedCalls")
    val estimatedCalls: List<EstimatedCall>? = null,
    @SerializedName("ExtensionX")
    val extension: ExtensionX? = null,
    @SerializedName("FramedVehicleJourneyRef")
    val framedVehicleJourneyRef: FramedVehicleJourneyRef? = null,
    @SerializedName("IsCompleteStopSequence")
    val isCompleteStopSequence: Boolean? = null,
    @SerializedName("LineRef")
    val lineRef: String? = null,
    @SerializedName("PublishedLineName")
    val publishedLineName: String? = null

)

data class EstimatedCall(
    @SerializedName("DestinationName")
    val destinationName: String? = null,
    @SerializedName("DestinationShortName")
    val destinationShortName: String? = null,
    @SerializedName("ExpectedArrivalTime")
    val expectedArrivalTime: String? = null,
    @SerializedName("ExpectedDepartureTime")
    val expectedDepartureTime: String? = null,
    @SerializedName("Extension")
    val extension: Extension? = null,
    @SerializedName("StopPointName")
    val stopPointName: String? = null,
    @SerializedName("StopPointRef")
    val stopPointRef: String? = null,
    @SerializedName("Via")
    val via: Any,
    @SerializedName("RealTime")
    val realTime: RealTime? = null
)

data class ExtensionX(
    @SerializedName("VehicleMode")
    val vehicleMode: String? = null
)

data class FramedVehicleJourneyRef(
    @SerializedName("DatedVehicleJourneySAERef")
    val datedVehicleJourneySAERef: String? = null
)

data class RealTime(
    @SerializedName("IsRealTime")
    val IsRealTime: Boolean? = null
)