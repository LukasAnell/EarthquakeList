package com.example.earthquakelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelist.api.EarthquakeService
import com.example.earthquakelist.api.RetrofitHelper
import com.example.earthquakelist.databinding.ActivityEarthquakeListBinding
import com.example.earthquakelist.models.Feature
import com.example.earthquakelist.models.FeatureCollection
import com.example.earthquakelist.models.Properties
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(EarthquakeService::class.java)
        val earthquakeCall = earthquakeService.getEarthquakeDataPastDay()
        earthquakeCall.enqueue(object: Callback<FeatureCollection> {
            override fun onResponse(
                call: Call<FeatureCollection>,
                response: Response<FeatureCollection>
            ) {
                // this is where you get your data
                // this is where you will set up your adapter for RecyclerView
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after response
                featureCollection = response.body()!!
                featureCollection.features = featureCollection.features
                    .sortedBy { -it.properties.mag }
                    .filter { it.properties.mag >= 1.0}
                refreshList()
                Log.d(TAG, "sorted: ${featureCollection.features}")
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}\n${t.stackTrace}")
            }

        })

        // loadJSON()
        // Log.d(TAG, "featureCollection: $featureCollection")
    }



    private fun refreshList() {
        val earthquakeAdapter = EarthquakeAdapter(featureCollection)

        val recyclerView: RecyclerView = binding.recyclerViewEarthquakeListEarthquakes
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = earthquakeAdapter
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sorting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.menu_item_sortByMagnitude -> {
                sortByMagnitude()
                refreshList()
                true
            }
            R.id.menu_item_sortByRecent -> {
                sortByRecent()
                refreshList()
                true
            }
            R.id.menu_item_help -> {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.apply {
                    setMessage("Purple: Significant (>6.5)\nRed: Large (4.5-6.5)\nOrange: " +
                            "Moderate (2.5-4.5)\nBlue: Small (1.0-2.5)\n\nThe number represents " +
                            "the magnitude of the earthquake.")
                }.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortByMagnitude() {
        featureCollection.features = featureCollection.features
            .sortedWith(
                compareBy<Feature> { -it.properties.mag }
                    .thenBy { -it.properties.time }
            )
    }

    private fun sortByRecent() {
        featureCollection.features = featureCollection.features.sortedBy { -it.properties.time }
    }


    /*
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
     */
}