package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName

data class Line(
    @SerializedName("Destinations")
    val destinations: List<Destination>,
    @SerializedName("Extension")
    val lineExtension: LineExtension,
    @SerializedName("LineName")
    val lineName: String,
    @SerializedName("LineRef")
    val lineRef: String
)