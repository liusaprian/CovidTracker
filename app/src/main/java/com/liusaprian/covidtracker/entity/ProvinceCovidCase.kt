package com.liusaprian.covidtracker.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseData(
	@field:SerializedName("attributes")
	val provinceCovidCase: ProvinceCovidCase
) : Parcelable

@Parcelize
data class ProvinceCovidCase(
	@field:SerializedName("Kasus_Meni")
	val kasusMeni: Int,

	@field:SerializedName("Kasus_Posi")
	val kasusPosi: Int,

	@field:SerializedName("Provinsi")
	val provinsi: String,

	@field:SerializedName("Kasus_Semb")
	val kasusSemb: Int
) : Parcelable
