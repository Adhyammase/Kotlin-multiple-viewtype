package com.ammase.kotlinmultipleviewtype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammase.kotlinmultipleviewtype.adapter.MainAdapter
import com.ammase.kotlinmultipleviewtype.data.DataModel.ListMain
import com.ammase.kotlinmultipleviewtype.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainAd by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAd
        }
        mainAd.setResultList(ListMain)

    }
}