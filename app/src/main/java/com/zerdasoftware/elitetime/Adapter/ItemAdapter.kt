package com.zerdasoftware.elitetime.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.zerdasoftware.elitetime.Activity.DetailActivity
import com.zerdasoftware.elitetime.Model.ItemsModel
import com.zerdasoftware.elitetime.databinding.ViewholderRecommendedBinding

class ItemAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(val binding: ViewholderRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$"+item.price.toString()
        holder.binding.ratingTxt.text = item.rating.toString()

        val requestOptions= RequestOptions().transform(CenterInside())

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}