package com.example.earthquakelist.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Metadata(
    val count: Int,
    val title: String,
    val status: Int
): Parcelable
