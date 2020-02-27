package br.com.inove_park_app.data.maps

import android.util.Log
import br.com.inove_park_app.R
import br.com.inove_park_app.util.LatLngUtil
import br.com.inove_park_app.util.LayoutUtil
import com.google.android.gms.maps.model.LatLng

interface MapsRepository {

    suspend fun direction(origin: LatLng, destination: LatLng): String
}

class MapsRepositoryImpl(private val mapsApi: MapsApi) : MapsRepository {

    override suspend fun direction(origin: LatLng, destination: LatLng): String {
        val key = LayoutUtil.getString(R.string.google_maps_key)
        val a = mapsApi.directions(
            LatLngUtil.toString(origin),
            LatLngUtil.toString(destination),
            key
        )
        Log.e("", a.toString())
        return ""
    }

}