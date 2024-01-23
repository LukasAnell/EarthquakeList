package com.example.earthquakelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Earthquake(
    val metadata: Metadata,
    val features: List<Feature>
): Parcelable
