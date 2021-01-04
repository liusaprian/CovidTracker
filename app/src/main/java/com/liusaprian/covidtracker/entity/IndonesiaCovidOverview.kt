package com.liusaprian.covidtracker.entity

import com.google.gson.annotations.SerializedName

data class IndonesiaCovidOverview(
    @field:SerializedName("lastUpdate")
    val lastUpdate: String? = null,

    @field:SerializedName("jumlahKasus")
    val caseCount: Int? = null,

    @field:SerializedName("sembuh")
    val recovered: Int? = null,

    @field:SerializedName("meninggal")
    val death: Int? = null
)