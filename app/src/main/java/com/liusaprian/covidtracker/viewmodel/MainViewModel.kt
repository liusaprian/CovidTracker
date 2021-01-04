package com.liusaprian.covidtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liusaprian.covidtracker.entity.IndonesiaCovidOverview
import com.liusaprian.covidtracker.repository.CaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private var repository = CaseRepository()

    private val indonesiaCovidCaseOverview = MutableLiveData<IndonesiaCovidOverview>()

    fun requestData() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getIndonesiaCovidCaseOverview()
            }
            indonesiaCovidCaseOverview.postValue(result)
        }
    }

    fun getData() : LiveData<IndonesiaCovidOverview> = indonesiaCovidCaseOverview

}