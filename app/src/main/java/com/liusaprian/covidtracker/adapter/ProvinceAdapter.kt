package com.liusaprian.covidtracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.liusaprian.covidtracker.R
import com.liusaprian.covidtracker.databinding.ProvinceDataBinding
import com.liusaprian.covidtracker.entity.ProvinceCovidCase
import com.liusaprian.covidtracker.entity.ResponseData

class ProvinceAdapter(private val data: ArrayList<ResponseData>) : RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder>() {

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ProvinceDataBinding.bind(itemView)
        fun bind(data: ProvinceCovidCase) {
            binding.provinceName.text = data.provinsi
            binding.provinceConfirmed.text = data.kasusPosi.toString()
            binding.provinceDeath.text = data.kasusMeni.toString()
            binding.provinceRecovered.text = data.kasusSemb.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.province_data, parent, false)
        return ProvinceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bind(data[position].provinceCovidCase)
    }

    override fun getItemCount() = data.size
}