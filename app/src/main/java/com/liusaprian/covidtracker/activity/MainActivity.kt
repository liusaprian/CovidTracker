package com.liusaprian.covidtracker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.liusaprian.covidtracker.R
import com.liusaprian.covidtracker.databinding.ActivityMainBinding
import com.liusaprian.covidtracker.entity.ResponseData
import com.liusaprian.covidtracker.viewmodel.MainViewModel
import com.liusaprian.covidtracker.viewmodel.ProvinceDataViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var provinceDataViewModel: ProvinceDataViewModel
    private var provinceCaseData = ArrayList<ResponseData>()
    private var load = false

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        showLoading(true)

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        provinceDataViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ProvinceDataViewModel::class.java
        )

        mainViewModel.requestData()
        provinceDataViewModel.requestData()

        mainViewModel.getData().observe(this, {
            if (it != null) {
                binding.confirmedCase.text = it.caseCount
                binding.deathCase.text = it.death
                binding.recoveryCase.text = it.recovered
                binding.lastUpdated.text = getString(
                    R.string.last_updated,
                    getCurrentDate()
                )
            }
        })

        provinceDataViewModel.getData().observe(this, {
            if(it != null) {
                provinceCaseData = it
//                provinceCaseData.removeAt(provinceCaseData.lastIndex)

                binding.firstProvince.text = it[0].provinceCovidCase.provinsi
                binding.firstConfirmed.text = it[0].provinceCovidCase.kasusPosi.toString()

                binding.secondProvince.text = it[1].provinceCovidCase.provinsi
                binding.secondConfirmed.text = it[1].provinceCovidCase.kasusPosi.toString()

                binding.thirdProvince.text = it[2].provinceCovidCase.provinsi
                binding.thirdConfirmed.text = it[2].provinceCovidCase.kasusPosi.toString()
            }
            showLoading(false)
        })

        binding.seeAll.setOnClickListener {
            if(load) {
                val toListActivity = Intent(this, ListActivity::class.java)
                toListActivity.putExtra(EXTRA_DATA, provinceCaseData)
                startActivity(toListActivity)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            load = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            load = true
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getCurrentDate() : String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = System.currentTimeMillis()
        return DateFormat.format("dd-MM-yyyy", calendar).toString()
    }
}
