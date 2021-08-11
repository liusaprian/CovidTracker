package com.liusaprian.covidtracker.entity

import com.google.gson.annotations.SerializedName

data class IndonesiaCovidOverview(
    @field:SerializedName("positif")
    val caseCount: String,

    @field:SerializedName("sembuh")
    val recovered: String,

    @field:SerializedName("meninggal")
    val death: String
)