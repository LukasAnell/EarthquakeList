package com.example.earthquakelist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feature(
    val properties: Properties,
    val geometry: Geometry
): Parcelable
