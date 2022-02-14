package com.ammase.kotlinmultipleviewtype.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ammase.kotlinmultipleviewtype.data.DataModel
import com.ammase.kotlinmultipleviewtype.data.DataModel.ListNews
import com.ammase.kotlinmultipleviewtype.databinding.ItemListMainBinding
import com.ammase.kotlinmultipleviewtype.databinding.ItemMainBannerBinding
import com.ammase.kotlinmultipleviewtype.databinding.ItemMainNewsBinding
import com.ammase.kotlinmultipleviewtype.model.MainModel

class MainAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var items = listOf<MainModel>()

    private val ITEM = 0
    private val BANNBER = 1
    private val NEWS = 2

    fun setResultList(listOfResult: List<MainModel>) {
        this.items = listOfResult
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM ->  ViewHolder(ItemListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            BANNBER -> ViewHolderBanner(ItemMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            NEWS -> ViewHolderNews(ItemMainNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Wrong view type")
        }
    }


    override fun getItemCount(): Int { return items.size }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            1 -> BANNBER
            3 -> NEWS
            else -> ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> { (holder as ViewHolder).bindUI(items[position])
            }
            BANNBER -> {

            }

            NEWS -> {

            }
        }

    }

    inner class ViewHolder(private val binding: ItemListMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bindUI(data: MainModel) {
            data.let {
                binding.textTitle.text = it.Title
            }
        }

    }

    inner class ViewHolderBanner(binding: ItemMainBannerBinding): RecyclerView.ViewHolder(binding.root) {
        private val bannerAd by lazy { HomeBannerAdapter() }
        init {
            Log.i("tesBanner", "show")

            with(binding){
                bannerViewPager.offscreenPageLimit = 1
                bannerViewPager.adapter = bannerAd
                dotsIndicator.setViewPager2(bannerViewPager)
                val transformer = ViewPager2.PageTransformer { page, position ->
                    page.apply {
                        val r = 1 - Math.abs(position)
                        page.alpha = 0.25f + r
                        page.scaleY = 0.75f + r * 0.25f
                    }
                }

                bannerViewPager.setPageTransformer(transformer)
                bannerAd.setResultList(DataModel.bannerList)
            }
        }
    }

    inner class ViewHolderNews(private val binding: ItemMainNewsBinding): RecyclerView.ViewHolder(binding.root){
        private val newsAd by lazy { HomeNewsAdapter() }
        init {
            binding.recyclweNews.isNestedScrollingEnabled = false
            binding.recyclweNews.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = newsAd
            }
            newsAd.setResultList(ListNews)

        }

    }

}

