package br.com.inove_park_app.util

import android.location.Location
import com.google.android.gms.maps.model.LatLng

object LatLngUtil {

    fun toString(latLng: LatLng?): String = "${latLng?.latitude}, ${latLng?.longitude}"

    fun toString(latitude: Double?, longitude: Double?): String = "$latitude, $longitude"

    fun toString(location: Location?): String = toString(location?.latitude, location?.longitude)

}