package com.liusaprian.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liusaprian.covidtracker.entity.ProvinceCovidCase
import com.liusaprian.covidtracker.repository.CaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProvinceDataViewModel : ViewModel() {

    private var repository = CaseRepository()

    private val provinceCovidData = MutableLiveData<ArrayList<ProvinceCovidCase>>()

    fun requestData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getAllProvinceCase().data
            }
            provinceCovidData.postValue(result)
        }
    }

    fun getData() : LiveData<ArrayList<ProvinceCovidCase>> = provinceCovidData

}