package com.example.glatalk_project.core.custom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glatalk_project.core.constant.Con

import kotlinx.android.synthetic.main.common_selector_item.view.*

class CountryAdapter: RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val countryList: ArrayList<Con.NationalCode> = arrayListOf(Con.NationalCode.ko, Con.NationalCode.en, Con.NationalCode.zh, Con.NationalCode.ja)
    private var onCountrySelectListener: OnCountrySelectListener? = null

    fun setCountrySelectListener(listener: OnCountrySelectListener) {
        onCountrySelectListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CommonSelectorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(countryList[position])
    }

    inner class CountryViewHolder(val binding: CommonSelectorItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            with(binding.root) {
                item_cl.setOnSingleClickListener {
                    onCountrySelectListener?.onCountrySelected(countryList[adapterPosition])
                }
            }
        }
        fun onBind(data: Con.NationalCode) {
            binding.item = data.country_nm
        }
    }

    interface OnCountrySelectListener {
        fun onCountrySelected(item: Con.NationalCode)
    }
}