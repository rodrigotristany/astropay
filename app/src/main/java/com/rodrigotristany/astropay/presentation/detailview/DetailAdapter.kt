package com.rodrigotristany.astropay.presentation.detailview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodrigotristany.astropay.databinding.WeatherInfoItemBinding

class DetailAdapter(private val detailModelList: ArrayList<DetailModel>):
        RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = WeatherInfoItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.binding.title.text = detailModelList[position].title
        holder.binding.description.text = detailModelList[position].description
    }

    override fun getItemCount(): Int = detailModelList.size

    fun refreshData(list: MutableList<DetailModel>) {
        detailModelList.clear()
        detailModelList.addAll(list)
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(val binding: WeatherInfoItemBinding)
        :RecyclerView.ViewHolder(binding.root)
}