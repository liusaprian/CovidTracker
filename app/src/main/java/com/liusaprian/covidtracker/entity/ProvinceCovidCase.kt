package com.liusaprian.covidtracker.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseData(
    @field:SerializedName("data")
    val data: ArrayList<ProvinceCovidCase>? = null
)

@Parcelize
data class ProvinceCovidCase(
    @field:SerializedName("provinsi")
    val provinceName: String? = null,

    @field:SerializedName("kasusPosi")
    val positiveCase: Int? = null,

    @field:SerializedName("kasusSemb")
    val recoveredCase: Int? = null,

    @field:SerializedName("kasusMeni")
    val deathCase: Int? = null
) : Parcelable
