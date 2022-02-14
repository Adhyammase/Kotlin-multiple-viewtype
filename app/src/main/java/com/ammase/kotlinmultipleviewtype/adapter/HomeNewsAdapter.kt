package com.ammase.kotlinmultipleviewtype.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ammase.kotlinmultipleviewtype.databinding.ItemListNewsBinding
import com.ammase.kotlinmultipleviewtype.model.HomeNewsModel

class HomeNewsAdapter() : RecyclerView.Adapter<HomeNewsAdapter.BannerViewHolder>() {
    private var mContext: Context? = null

    private var items = listOf<HomeNewsModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding = ItemListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        mContext = parent.context
        return BannerViewHolder(itemBinding)

    }

    fun setResultList(listOfResult: List<HomeNewsModel>) {
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

    inner class BannerViewHolder(private val binding: ItemListNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: HomeNewsModel){
            with(binding){
                textName.text = data.name
                textDate.text = data.date

                Glide.with(mContext!!)
                    .load(data.imageUrl)
                    .apply(
                        RequestOptions()
                            .centerCrop()
                            .override(512,512))
                    .into(imgImage)
            }

        }
    }
}

