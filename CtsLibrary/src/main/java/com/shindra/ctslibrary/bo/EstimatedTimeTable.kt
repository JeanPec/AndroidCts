package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName
import com.shindra.arrakis.extension.isValid
import java.time.Duration
import java.util.*


data class EstimatedTimeTable(
    @SerializedName("ServiceDelivery")
    val serviceDelivery: ServiceDelivery
) : CtsPersistentBo() {

    override fun isValid(dateOfInsertion: Date): Boolean {
        val shortestPossibleCycle = serviceDelivery.estimatedTimetableDelivery.first().shortestPossibleCycle
        return dateOfInsertion.isValid(Duration.parse(shortestPossibleCycle))
    }
}

data class ServiceDelivery(
    @SerializedName("EstimatedTimetableDelivery")
    val estimatedTimetableDelivery: List<EstimatedTimetableDelivery>,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String
)

data class EstimatedTimetableDelivery(
    @SerializedName("EstimatedJourneyVersionFrame")
    val estimatedJourneyVersionFrame: List<EstimatedJourneyVersionFrame>,
    @SerializedName("ResponseTimestamp")
    val responseTimestamp: String,
    @SerializedName("ShortestPossibleCycle")
    val shortestPossibleCycle: String,
    @SerializedName("ValidUntil")
    val validUntil: String,
    val version: String
)

data class EstimatedJourneyVersionFrame(
    @SerializedName("EstimatedVehicleJourney")
    val estimatedVehicleJourney: List<EstimatedVehicleJourney>,
    @SerializedName("RecordedAtTime")
    val recordedAtTime: String
)

data class EstimatedVehicleJourney(
    @SerializedName("DirectionRef")
    val directionRef: Int,
    @SerializedName("EstimatedCalls")
    val estimatedCalls: List<EstimatedCall>,
    @SerializedName("ExtensionX")
    val extension: ExtensionX,
    @SerializedName("FramedVehicleJourneyRef")
    val framedVehicleJourneyRef: FramedVehicleJourneyRef,
    @SerializedName("IsCompleteStopSequence")
    val isCompleteStopSequence: Boolean,
    @SerializedName("LineRef")
    val lineRef: String,
    @SerializedName("PublishedLineName")
    val publishedLineName: String

)

data class EstimatedCall(
    @SerializedName("DestinationName")
    val destinationName: String,
    @SerializedName("DestinationShortName")
    val destinationShortName: String,
    @SerializedName("ExpectedArrivalTime")
    val expectedArrivalTime: String,
    @SerializedName("ExpectedDepartureTime")
    val expectedDepartureTime: String,
    @SerializedName("Extension")
    val extension: Extension,
    @SerializedName("StopPointName")
    val stopPointName: String,
    @SerializedName("StopPointRef")
    val stopPointRef: String,
    @SerializedName("Via")
    val via: Any,
    @SerializedName("RealTime")
    val realTime: RealTime
)

data class ExtensionX(
    @SerializedName("VehicleMode")
    val vehicleMode: String
)

data class FramedVehicleJourneyRef(
    @SerializedName("DatedVehicleJourneySAERef")
    val datedVehicleJourneySAERef: String
)

data class RealTime(
    @SerializedName("IsRealTime")
    val IsRealTime: Boolean
)