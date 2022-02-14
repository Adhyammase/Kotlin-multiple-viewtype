package com.ammase.kotlinmultipleviewtype.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ammase.kotlinmultipleviewtype.databinding.BannerItemLayoutBinding
import com.ammase.kotlinmultipleviewtype.model.HomeBannerModel

class HomeBannerAdapter() : RecyclerView.Adapter<HomeBannerAdapter.BannerViewHolder>() {

    private var items = listOf<HomeBannerModel>()

    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding = BannerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return BannerViewHolder(itemBinding)

    }

    fun setResultList(listOfResult: List<HomeBannerModel>) {
        this.items = listOfResult
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val bannerItem = items[position]
        holder.bind(bannerItem)
    }

    inner class BannerViewHolder(private val binding: BannerItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(bannerItem: HomeBannerModel){
            with(binding){
                Glide.with(mContext!!)
                    .load(bannerItem.imageUrl)
                    .apply(
                        RequestOptions()
                            .centerCrop())
                    .into(bannerImageView)
            }

        }
    }
}

