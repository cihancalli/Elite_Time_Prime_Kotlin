package com.zerdasoftware.elitetime.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.elitetime.Adapter.CartAdapter
import com.zerdasoftware.elitetime.Helper.ChangeNumberItemsListener
import com.zerdasoftware.elitetime.Helper.ManagementChart
import com.zerdasoftware.elitetime.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var managementChart: ManagementChart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementChart = ManagementChart(this)

        setVariable()
        initCartList()
        calculateCart()

    }

    private fun initCartList() {
        binding.viewCart.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewCart.adapter =
            CartAdapter(managementChart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
        with(binding) {
            emptyTxt.visibility =
                if (managementChart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView.visibility =
                if (managementChart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managementChart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managementChart.getTotalFee() + tax + delivery) * 100) / 100
        val itemTotal = Math.round(managementChart.getTotalFee() * 100) / 100

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }

    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}