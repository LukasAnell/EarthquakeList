package com.example.earthquakelist.api

import com.example.earthquakelist.models.FeatureCollection
import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeService {
    // where you list out the different endpoints in the API you want to call
    // function returns Call<Type> where type is the data returned in the JSON
    // in the @GET("blah"), "blah" is the path to the endpoint

    @GET("all_day.geojson")
    fun getEarthquakeDataPastDay(): Call<FeatureCollection>
}