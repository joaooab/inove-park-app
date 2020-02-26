package br.com.inove_park_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeViewModel : ViewModel() {

    private val markerList = listOf(
        MarkerOptions().position(
            LatLng(
                -16.682378,
                -49.262957
            )
        ).title("Estacionamento 1"),
        MarkerOptions().position(
            LatLng(
                -16.681987,
                -49.263075
            )
        ).title("Estacionamento 2"),
        MarkerOptions().position(
            LatLng(
                -16.681196,
                -49.263300
            )
        ).title("Estacionamento 3")
    )

    fun getMarkerList(): List<MarkerOptions> = markerList

    fun getFirstLatLong(): LatLng = markerList[0].position
}