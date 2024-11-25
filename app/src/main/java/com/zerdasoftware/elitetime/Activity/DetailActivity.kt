package com.zerdasoftware.elitetime.Activity

import android.os.Bundle
import com.zerdasoftware.elitetime.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}