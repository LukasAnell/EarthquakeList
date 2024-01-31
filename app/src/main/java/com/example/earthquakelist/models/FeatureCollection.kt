package com.example.earthquakelist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureCollection(
    val metadata: Metadata,
    var features: List<Feature>
): Parcelable
