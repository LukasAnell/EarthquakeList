package com.example.earthquakelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelist.models.FeatureCollection
import java.util.Date

class EarthquakeAdapter(private var featureCollection: FeatureCollection): RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val magnitudeTextView: TextView
        val locationTextView: TextView
        val timeTextView: TextView
        val layout: ConstraintLayout
        init {
            magnitudeTextView = view.findViewById(R.id.textView_earthquakeItem_magnitude)
            locationTextView = view.findViewById(R.id.textView_earthquakeItem_location)
            timeTextView = view.findViewById(R.id.textView_earthquakeItem_time)
            layout = view.findViewById(R.id.layout_itemEarthquake)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_earthquake, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // val context = viewHolder.layout.context
        val earthquake = featureCollection.features[position]
        viewHolder.magnitudeTextView.text = earthquake.properties.mag.toString()
        viewHolder.locationTextView.text = earthquake.properties.place
        viewHolder.timeTextView.text = Date(earthquake.properties.time).toString()
    }

    override fun getItemCount() = featureCollection.features.size
}