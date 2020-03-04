package br.com.inove_park_app.data.maps

import br.com.inove_park_app.data.google.Direction
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {

    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String, @Query("destination") destination: String, @Query("key") key: String
    ): Direction

}