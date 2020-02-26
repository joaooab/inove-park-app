package br.com.inove_park_app.util

import com.google.android.gms.maps.model.LatLng

object LatLngUtil {

    fun toString(latLng: LatLng): String = "${latLng.latitude},${latLng.longitude}"

}