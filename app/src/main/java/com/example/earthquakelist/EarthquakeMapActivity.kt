package com.example.earthquakelist

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.earthquakelist.databinding.ActivityEarthquakeMapBinding
import com.example.earthquakelist.models.Feature
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.util.Date


class EarthquakeMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeMapBinding
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map: MapView
    private lateinit var earthquake: Feature
    companion object {
        const val TAG = "EarthquakeMakeActivity"
        val EXTRA_EARTHQUAKE = "earthquake"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        binding = ActivityEarthquakeMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        earthquake = intent.getParcelableExtra(EXTRA_EARTHQUAKE)!!
        Log.d(TAG, "earthquake: $earthquake")

        initializeMap()
    }

    private fun initializeMap() {
        map = binding.mapEarthquakeMap
        map.setTileSource(TileSourceFactory.MAPNIK)
        val mapController = map.controller
        mapController.setZoom(9.5)
        mapController.setCenter(GeoPoint(earthquake.geometry.coordinates[1], earthquake.geometry.coordinates[0]))
        val rotationGestureOverlay = RotationGestureOverlay(map)
        rotationGestureOverlay.isEnabled
        map.setMultiTouchControls(true)
        map.overlays.add(rotationGestureOverlay)
        val dm : DisplayMetrics = this.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(map)
        scaleBarOverlay.setCentred(true)
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10)
        map.overlays.add(scaleBarOverlay)

        // OverlayItem(earthquake.properties.title, Date(earthquake.properties.time).toString(), GeoPoint(earthquake.geometry.coordinates[1], earthquake.geometry.coordinates[0]))

    }

    override fun onResume() {
        super.onResume()

        map.onResume()
    }

    override fun onPause() {
        super.onPause()

        map.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>()
        var i = 0
        while(i < grantResults.size) {
            permissionsToRequest.add(permissions[i])
            i++
        }
        if(permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }
}