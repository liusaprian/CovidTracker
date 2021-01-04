package com.liusaprian.covidtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liusaprian.covidtracker.R
import com.liusaprian.covidtracker.databinding.ProvinceDataBinding
import com.liusaprian.covidtracker.entity.ProvinceCovidCase

class ProvinceAdapter(private val data: ArrayList<ProvinceCovidCase>) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProvinceDataBinding.bind(itemView)
        fun bind(data: ProvinceCovidCase) {
            binding.provinceName.text = data.provinceName
            binding.provinceConfirmed.text = data.positiveCase.toString()
            binding.provinceDeath.text = data.deathCase.toString()
            binding.provinceRecovered.text = data.recoveredCase.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.province_data, parent, false)
        return ProvinceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}