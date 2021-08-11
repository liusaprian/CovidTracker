package com.liusaprian.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liusaprian.covidtracker.entity.ResponseData
import com.liusaprian.covidtracker.repository.CaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProvinceDataViewModel : ViewModel() {

    private var repository = CaseRepository()

    private val provinceCovidData = MutableLiveData<ArrayList<ResponseData>>()

    fun requestData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getAllProvinceCase()
            }
            provinceCovidData.postValue(result)
        }
    }

    fun getData() : LiveData<ArrayList<ResponseData>> = provinceCovidData

}