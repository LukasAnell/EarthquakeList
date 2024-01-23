package com.example.earthquakelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earthquakelist.databinding.ActivityEarthquakeListBinding

class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding

    companion object {
        const val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}