@file:Suppress("DEPRECATION")

package com.zerdasoftware.elitetime.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.elitetime.Adapter.ColorAdapter
import com.zerdasoftware.elitetime.Adapter.SizeAdapter
import com.zerdasoftware.elitetime.Adapter.SliderAdapter
import com.zerdasoftware.elitetime.Helper.ManagementChart
import com.zerdasoftware.elitetime.Model.ItemsModel
import com.zerdasoftware.elitetime.Model.SliderModel
import com.zerdasoftware.elitetime.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managementChart: ManagementChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementChart = ManagementChart(this)

        getBundle()
        banners()
        initLists()

    }

    private fun initLists() {
        val sizeList = ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun banners() {
        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1

        if (sliderItems.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)

        }
    }

    @SuppressLint("SetTextI18n")
    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "$" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managementChart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {

        }

    }
}