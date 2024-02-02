package com.example.earthquakelist

import android.content.Intent
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
        val context = viewHolder.layout.context
        val earthquake = featureCollection.features[position]
        val magnitude = earthquake.properties.mag
        val place = earthquake.properties.place
        val date = Date(earthquake.properties.time).toString()

        when {
            // significant
            magnitude > 6.5 -> {
                viewHolder.magnitudeTextView.setTextColor(context.resources.getColor(R.color.significant, context.theme))
                viewHolder.magnitudeTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
            }
            // large
            magnitude <= 6.5 && magnitude > 4.5 -> {
                viewHolder.magnitudeTextView.setTextColor(context.resources.getColor(R.color.large, context.theme))
                viewHolder.magnitudeTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
            }
            // moderate
            magnitude <= 4.5 && magnitude > 2.5 -> {
                viewHolder.magnitudeTextView.setTextColor(context.resources.getColor(R.color.moderate, context.theme))
                viewHolder.magnitudeTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
            }
            // small
            magnitude <= 2.5 && magnitude > 1 -> {
                viewHolder.magnitudeTextView.setTextColor(context.resources.getColor(R.color.small, context.theme))
                viewHolder.magnitudeTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0)
            }
        }

        viewHolder.magnitudeTextView.text = String.format("%.2f", magnitude)
        viewHolder.locationTextView.text = place
        viewHolder.timeTextView.text = date

        viewHolder.layout.setOnClickListener {
            // Toast.makeText(context, hero.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, EarthquakeMapActivity::class.java)

            intent.putExtra(EarthquakeMapActivity.EXTRA_EARTHQUAKE, earthquake)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = featureCollection.features.size
}