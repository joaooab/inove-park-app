package br.com.inove_park_app.data.maps

import br.com.inove_park_app.R
import br.com.inove_park_app.data.google.Direction
import br.com.inove_park_app.util.LayoutUtil
import com.google.android.gms.maps.model.LatLng

interface MapsRepository {

    suspend fun getDirections(origin: String, destination: String): Direction

}

class MapsRepositoryImpl(private val mapsApi: MapsApi) : MapsRepository {

    override suspend fun getDirections(origin: String, destination: String): Direction {
        val key = LayoutUtil.getString(R.string.google_maps_key)
        return mapsApi.getDirections(origin, destination, key)
    }

}