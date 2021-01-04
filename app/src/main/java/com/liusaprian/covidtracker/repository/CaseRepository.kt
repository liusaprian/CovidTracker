package com.liusaprian.covidtracker.repository

import com.liusaprian.covidtracker.network.ApiService
import com.liusaprian.covidtracker.network.myApi

class CaseRepository {
    private var service: ApiService = myApi

    suspend fun getIndonesiaCovidCaseOverview() = service.getIndonesiaCaseOverview()

    suspend fun getAllProvinceCase() = service.getAllProvinceCase()
}