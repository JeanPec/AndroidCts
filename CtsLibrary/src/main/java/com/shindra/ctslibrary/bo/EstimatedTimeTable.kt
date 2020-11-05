package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName

data class EstimatedTimeTable(
    @SerializedName("AccessInformation_DE")
    val ServiceDelivery: ServiceDelivery
) : CtsPersistentObject()

data class ServiceDelivery(
    @SerializedName("AccessInformation_DE")
    val EstimatedTimetableDelivery: List<EstimatedTimetableDelivery>,
    @SerializedName("AccessInformation_DE")
    val GeneralMessageDelivery: List<GeneralMessageDelivery>,
    @SerializedName("AccessInformation_DE")
    val RequestMessageRef: String,
    @SerializedName("AccessInformation_DE")
    val ResponseTimestamp: String,
    @SerializedName("AccessInformation_DE")
    val StopMonitoringDelivery: List<StopMonitoringDelivery>,
    @SerializedName("AccessInformation_DE")
    val VehicleMonitoringDelivery: List<VehicleMonitoringDelivery>
)

data class EstimatedTimetableDelivery(
    @SerializedName("AccessInformation_DE")
    val EstimatedJourneyVersionFrame: List<EstimatedJourneyVersionFrame>,
    @SerializedName("AccessInformation_DE")
    val ResponseTimestamp: String,
    @SerializedName("AccessInformation_DE")
    val ShortestPossibleCycle: String,
    @SerializedName("AccessInformation_DE")
    val ValidUntil: String,
    @SerializedName("AccessInformation_DE")
    val version: String
)

data class GeneralMessageDelivery(
    @SerializedName("AccessInformation_DE")
    val InfoMessage: List<InfoMessage>,
    @SerializedName("AccessInformation_DE")
    val ResponseTimestamp: String,
    @SerializedName("AccessInformation_DE")
    val ShortestPossibleCycle: String,
    @SerializedName("AccessInformation_DE")
    val version: String
)

data class StopMonitoringDelivery(
    @SerializedName("AccessInformation_DE")
    val MonitoredStopVisit: List<MonitoredStopVisit>,
    @SerializedName("AccessInformation_DE")
    val MonitoringRef: List<String>,
    @SerializedName("AccessInformation_DE")
    val ResponseTimestamp: String,
    @SerializedName("AccessInformation_DE")
    val ShortestPossibleCycle: String,
    @SerializedName("AccessInformation_DE")
    val ValidUntil: String,
    @SerializedName("AccessInformation_DE")
    val version: String
)

data class VehicleMonitoringDelivery(
    @SerializedName("AccessInformation_DE")
    val ResponseTimestamp: String,
    @SerializedName("AccessInformation_DE")
    val ShortestPossibleCycle: String,
    @SerializedName("AccessInformation_DE")
    val ValidUntil: String,
    @SerializedName("AccessInformation_DE")
    val VehicleActivity: List<VehicleActivity>
)

data class EstimatedJourneyVersionFrame(
    @SerializedName("AccessInformation_DE")
    val EstimatedVehicleJourney: List<EstimatedVehicleJourney>,
    @SerializedName("AccessInformation_DE")
    val RecordedAtTime: String
)

data class EstimatedVehicleJourney(
    @SerializedName("AccessInformation_DE")
    val DirectionRef: Int,
    @SerializedName("AccessInformation_DE")
    val EstimatedCalls: List<EstimatedCall>,
    @SerializedName("AccessInformation_DE")
    val Extension: ExtensionX,
    @SerializedName("AccessInformation_DE")
    val FramedVehicleJourneyRef: FramedVehicleJourneyRef,
    @SerializedName("AccessInformation_DE")
    val IsCompleteStopSequence: Boolean,
    @SerializedName("AccessInformation_DE")
    val LineRef: String,
    @SerializedName("AccessInformation_DE")
    val PublishedLineName: String
)

data class EstimatedCall(
    @SerializedName("AccessInformation_DE")
    val DestinationName: String,
    @SerializedName("AccessInformation_DE")
    val DestinationShortName: String,
    @SerializedName("AccessInformation_DE")
    val ExpectedArrivalTime: String,
    @SerializedName("AccessInformation_DE")
    val ExpectedDepartureTime: String,
    @SerializedName("AccessInformation_DE")
    val Extension: Extension,
    @SerializedName("AccessInformation_DE")
    val StopPointName: String,
    @SerializedName("AccessInformation_DE")
    val StopPointRef: String,
    @SerializedName("AccessInformation_DE")
    val Via: String
)

data class ExtensionX(
    @SerializedName("AccessInformation_DE")
    val VehicleMode: String
)

data class FramedVehicleJourneyRef(
    @SerializedName("AccessInformation_DE")
    val DatedVehicleJourneySAERef: String
)

data class Extension(
    @SerializedName("AccessInformation_DE")
    val IsRealTime: Boolean
)

data class InfoMessage(
    @SerializedName("AccessInformation_DE")
    val Content: Content,
    @SerializedName("AccessInformation_DE")
    val InfoChannelRef: String,
    @SerializedName("AccessInformation_DE")
    val InfoMessageIdentifier: String,
    @SerializedName("AccessInformation_DE")
    val ItemIdentifier: String,
    @SerializedName("AccessInformation_DE")
    val RecordedAtTime: String,
    @SerializedName("AccessInformation_DE")
    val ValidUntilTime: String,
    @SerializedName("AccessInformation_DE")
    val formatRef: String
)

data class Content(
    @SerializedName("AccessInformation_DE")
    val ImpactEndDateTime: String,
    @SerializedName("AccessInformation_DE")
    val ImpactStartDateTime: String,
    @SerializedName("AccessInformation_DE")
    val ImpactedGroupOfLinesRef: String,
    @SerializedName("AccessInformation_DE")
    val ImpactedLineRef: List<String>,
    @SerializedName("AccessInformation_DE")
    val Message: List<Message>,
    @SerializedName("AccessInformation_DE")
    val Priority: String,
    @SerializedName("AccessInformation_DE")
    val TypeOfPassengerEquipmentRef: String
)

data class Message(
    @SerializedName("AccessInformation_DE")
    val MessageText: List<MessageText>,
    @SerializedName("AccessInformation_DE")
    val MessageZoneRef: String
)

data class MessageText(
    @SerializedName("AccessInformation_DE")
    val Lang: String,
    @SerializedName("AccessInformation_DE")
    val Value: String
)

data class MonitoredStopVisit(
    @SerializedName("AccessInformation_DE")
    val MonitoredVehicleJourney: MonitoredVehicleJourney,
    @SerializedName("AccessInformation_DE")
    val MonitoringRef: String,
    @SerializedName("AccessInformation_DE")
    val RecordedAtTime: String,
    @SerializedName("AccessInformation_DE")
    val StopCode: String
)

data class MonitoredVehicleJourney(
    @SerializedName("AccessInformation_DE")
    val DestinationName: String,
    @SerializedName("AccessInformation_DE")
    val DestinationShortName: String,
    @SerializedName("AccessInformation_DE")
    val DirectionRef: Int,
    @SerializedName("AccessInformation_DE")
    val FramedVehicleJourneyRef: FramedVehicleJourneyRefX,
    @SerializedName("AccessInformation_DE")
    val LineRef: String,
    @SerializedName("AccessInformation_DE")
    val MonitoredCall: MonitoredCall,
    @SerializedName("AccessInformation_DE")
    val OnwardCall: List<OnwardCall>,
    @SerializedName("AccessInformation_DE")
    val PreviousCall: List<PreviousCall>,
    @SerializedName("AccessInformation_DE")
    val PublishedLineName: String,
    @SerializedName("AccessInformation_DE")
    val VehicleMode: String,
    @SerializedName("AccessInformation_DE")
    val Via: String
)

data class FramedVehicleJourneyRefX(
    @SerializedName("AccessInformation_DE")
    val DatedVehicleJourneySAERef: String
)

data class MonitoredCall(
    @SerializedName("AccessInformation_DE")
    val ExpectedArrivalTime: String,
    @SerializedName("AccessInformation_DE")
    val ExpectedDepartureTime: String,
    @SerializedName("AccessInformation_DE")
    val Extension: ExtensionXX,
    @SerializedName("AccessInformation_DE")
    val Order: Int,
    @SerializedName("AccessInformation_DE")
    val StopCode: String,
    @SerializedName("AccessInformation_DE")
    val StopPointName: String

)

data class OnwardCall(
    @SerializedName("AccessInformation_DE")
    val ExpectedArrivalTime: String,
    @SerializedName("AccessInformation_DE")
    val ExpectedDepartureTime: String,
    @SerializedName("AccessInformation_DE")
    val Order: Int,
    @SerializedName("AccessInformation_DE")
    val StopCode: String,
    @SerializedName("AccessInformation_DE")
    val StopPointName: String
)

data class PreviousCall(
    @SerializedName("AccessInformation_DE")
    val Order: Int,
    @SerializedName("AccessInformation_DE")
    val StopCode: String,
    @SerializedName("AccessInformation_DE")
    val StopPointName: String
)

data class ExtensionXX(
    @SerializedName("AccessInformation_DE")
    val DataSource: String,
    @SerializedName("AccessInformation_DE")
    val IsRealTime: Boolean
)

data class VehicleActivity(
    @SerializedName("AccessInformation_DE")
    val MonitoredVehicleJourney: MonitoredVehicleJourneyX,
    @SerializedName("AccessInformation_DE")
    val RecordedAtTime: String
)

data class MonitoredVehicleJourneyX(
    @SerializedName("DestinationName")
    val destinationName: String,
    @SerializedName("DestinationShortName")
    val destinationShortName: String,
    @SerializedName("DirectionRef")
    val directionRef: Int,
    @SerializedName("FramedVehicleJourneyRef")
    val framedVehicleJourneyRef: FramedVehicleJourneyRefXX,
    @SerializedName("LineRef")
    val lineRef: String,
    @SerializedName("MonitoredCall")
    val monitoredCall: MonitoredCallX,
    @SerializedName("OnwardCall")
    val onwardCall: List<OnwardCallX>,
    @SerializedName("PreviousCall")
    val previousCall: List<PreviousCallX>,
    @SerializedName("PublishedLineName")
    val publishedLineName: String,
    @SerializedName("VehicleMode")
    val vehicleMode: String,
    @SerializedName("Via")
    val via: String
)

data class FramedVehicleJourneyRefXX(
    @SerializedName("DatedVehicleJourneySAERef")
    val datedVehicleJourneySAERef: String
)

data class MonitoredCallX(
    @SerializedName("ExpectedArrivalTime")
    val expectedArrivalTime: String,
    @SerializedName("ExpectedDepartureTime")
    val expectedDepartureTime: String,
    @SerializedName("Extension")
    val extension: ExtensionXXX,
    @SerializedName("Order")
    val order: Int,
    @SerializedName("StopCode")
    val stopCode: String,
    @SerializedName("StopPointName")
    val stopPointName: String
)

data class OnwardCallX(
    @SerializedName("ExpectedArrivalTime")
    val expectedArrivalTime: String,
    @SerializedName("ExpectedDepartureTime")
    val expectedDepartureTime: String,
    @SerializedName("Order")
    val order: Int,
    @SerializedName("StopCode")
    val stopCode: String,
    @SerializedName("StopPointName")
    val stopPointName: String
)

data class PreviousCallX(
    @SerializedName("Order")
    val order: Int,
    @SerializedName("StopCode")
    val stopCode: String,
    @SerializedName("StopPointName")
    val stopPointName: String
)

data class ExtensionXXX(
    @SerializedName("DataSource")
    val dataSource: String,
    @SerializedName("IsRealTime")
    val isRealTime: Boolean
)