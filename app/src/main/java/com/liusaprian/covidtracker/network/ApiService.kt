package com.liusaprian.covidtracker.network

import com.liusaprian.covidtracker.entity.IndonesiaCovidOverview
import com.liusaprian.covidtracker.entity.ResponseData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET(".")
    suspend fun getIndonesiaCaseOverview() : List<IndonesiaCovidOverview>

    @GET("provinsi")
    suspend fun getAllProvinceCase() : ArrayList<ResponseData>
}

private const val BASE_URL = "https://api.kawalcorona.com/indonesia/"

val myApi: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}