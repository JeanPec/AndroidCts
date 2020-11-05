package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName

data class LineExtension(
    @SerializedName("RouteColor")
    val routeColor: String,
    @SerializedName("RouteTextColor")
    val routeTextColor: String,
    @SerializedName("RouteType")
    val routeType: RouteType
)

enum class RouteType{
    @SerializedName("tram")
    TRAM,
    @SerializedName("bus")
    BUS,
    @SerializedName("undefined")
    UNDEFINED
}