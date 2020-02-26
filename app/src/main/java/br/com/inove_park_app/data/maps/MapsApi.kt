package br.com.inove_park_app.data.maps

import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {

    @GET("directions/json")
    suspend fun directions(
        @Query("origin") origin: String, @Query("destination") destination: String, @Query("key") key: String
    ): String

}