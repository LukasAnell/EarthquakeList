package com.example.earthquakelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.earthquakelist.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var featureCollection: FeatureCollection

    companion object {
        const val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadJSON()
        Log.d(TAG, "featureCollection: $featureCollection")

    }

    private fun loadJSON() {
        val inputStream = resources.openRawResource(R.raw.test)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val sType = object: TypeToken<FeatureCollection>() { }.type
        val otherList = gson.fromJson<FeatureCollection>(jsonString, sType)

        featureCollection = otherList
    }
}