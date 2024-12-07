package com.zerdasoftware.elitetime.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.zerdasoftware.elitetime.Helper.ChangeNumberItemsListener
import com.zerdasoftware.elitetime.Helper.ManagementChart
import com.zerdasoftware.elitetime.Model.ItemsModel
import com.zerdasoftware.elitetime.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val managementChart = ManagementChart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$${item.price}"
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.numberItemTxt.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener {
            managementChart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
        }

        holder.binding.minesCartBtn.setOnClickListener {
            managementChart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

    }

    override fun getItemCount(): Int = listItemSelected.size
}