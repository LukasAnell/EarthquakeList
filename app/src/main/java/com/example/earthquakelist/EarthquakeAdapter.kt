package com.example.earthquakelist

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelist.models.Feature
import com.example.earthquakelist.models.FeatureCollection

class EarthquakeAdapter(private var featureCollection: FeatureCollection): RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val magnitudeTextView: TextView
        val locationTextView: TextView
        val timeTextView: TextView
        val layout: ConstraintLayout
        init {
            magnitudeTextView = view.findViewById(R.id.)
            locationTextView = view.findViewById(R.id.)
            timeTextView = view.findViewById(R.id.)
            layout = view.findViewById(R.id.layout_itemEarthquake)
        }
    }
}