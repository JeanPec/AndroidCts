package com.shindra.ctslibrary.apibo

import com.google.gson.annotations.SerializedName

data class LineApi(
    @SerializedName("AnnotatedLineRef")
    val destinations: List<Destination>? = null,
    @SerializedName("Extension")
    val lineExtension: LineExtension? = null,
    @SerializedName("LineName")
    val lineName: String? = null,
    @SerializedName("LineRef")
    val lineRef: String? = null
)