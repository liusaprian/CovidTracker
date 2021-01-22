package com.liusaprian.covidtracker.activity

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.liusaprian.covidtracker.activity.MainActivity.Companion.EXTRA_DATA
import com.liusaprian.covidtracker.R
import com.liusaprian.covidtracker.adapter.ProvinceAdapter
import com.liusaprian.covidtracker.databinding.ActivityListBinding
import com.liusaprian.covidtracker.entity.ProvinceCovidCase
import java.util.*
import kotlin.collections.ArrayList

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var provinceDataAdapter: ProvinceAdapter
    private var provinceCovidDataList = ArrayList<ProvinceCovidCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableArrayListExtra<ProvinceCovidCase>(EXTRA_DATA)?.let {
            provinceCovidDataList = it
        }

        setRecyclerViewData(provinceCovidDataList)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.list_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String) = true

            override fun onQueryTextChange(query: String): Boolean {
                if(query.isEmpty()) setRecyclerViewData(provinceCovidDataList)
                else {
                    showLoading(true)
                    searchProvince(query)
                    showLoading(false)
                }
                return true
            }

        })
        return true
    }

    private fun searchProvince(query: String) {
        if(query.isNotEmpty()) {
            val searchMatchData = ArrayList<ProvinceCovidCase>()
            for(provinceCovidData in provinceCovidDataList)
                if (provinceCovidData.provinceName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase(Locale.ROOT)) == true)
                    searchMatchData.add(provinceCovidData)
            setRecyclerViewData(searchMatchData)
        }
    }

    private fun setRecyclerViewData(data: ArrayList<ProvinceCovidCase>) {
        provinceDataAdapter = ProvinceAdapter(data)
        provinceDataAdapter.notifyDataSetChanged()

        binding.provinceList.layoutManager = LinearLayoutManager(this)
        binding.provinceList.adapter = provinceDataAdapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if(state) View.VISIBLE else View.GONE
    }
}