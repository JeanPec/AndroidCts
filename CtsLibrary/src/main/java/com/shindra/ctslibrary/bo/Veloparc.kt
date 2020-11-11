package com.shindra.ctslibrary.bo

import com.google.gson.annotations.SerializedName

data class VeloParcs(@SerializedName("Veloparc") val veloparc: List<Veloparc>) : CtsPersistentBo()

data class Veloparc(
    @SerializedName("AccessInformation_DE")
    val germanAccessInformation: String,
    @SerializedName("AccessInformation_EN")
    val englishAccessInformation: String,
    @SerializedName("AccessInformation_FR")
    val frenchAccessInformation: String,
    @SerializedName("Designation")
    val designation: String,
    @SerializedName("Latitude")
    val latitude: Double,
    @SerializedName("Longitude")
    val longitude: Double
)