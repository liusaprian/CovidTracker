package com.liusaprian.covidtracker.activity

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
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

        handleIntent(intent)

        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.list_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onNewIntent(intent: Intent) {
        setIntent(intent)
        handleIntent(intent)
        super.onNewIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                showLoading(true)
                searchProvince(query)
                showLoading(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                super.onSearchRequested()
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun searchProvince(query: String) {
        if(query.isNotEmpty()) {
            val searchMatchData = ArrayList<ProvinceCovidCase>()
            for(provinceCovidData in provinceCovidDataList)
                if (provinceCovidData.provinceName?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase(Locale.ROOT)) == true)
                    searchMatchData.add(provinceCovidData)
            if(searchMatchData.isEmpty()) Toast.makeText(this, getString(R.string.empty_result), Toast.LENGTH_LONG).show()
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