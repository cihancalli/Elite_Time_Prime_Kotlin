package com.zerdasoftware.elitetime.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.zerdasoftware.elitetime.Model.BrandModel
import com.zerdasoftware.elitetime.Model.ItemModel
import com.zerdasoftware.elitetime.Model.SliderModel

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _item = MutableLiveData<MutableList<ItemModel>>()

    val banners: LiveData<List<SliderModel>> = _banner
    val brands: LiveData<MutableList<BrandModel>> = _brand
    val items: LiveData<MutableList<ItemModel>> = _item

    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun loadBrands() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadItems() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _item.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}