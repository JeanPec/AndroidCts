package com.shindra.ctslibrary.apibo

import com.google.gson.annotations.SerializedName

data class LineExtension(
    @SerializedName("RouteColor")
    val routeColor: String? = null,
    @SerializedName("RouteTextColor")
    val routeTextColor: String? = null,
    @SerializedName("RouteType")
    val routeType: RouteType? = null
)

enum class RouteType{
    @SerializedName("tram")
    TRAM,
    @SerializedName("bus")
    BUS,
    @SerializedName("undefined")
    UNDEFINED
}