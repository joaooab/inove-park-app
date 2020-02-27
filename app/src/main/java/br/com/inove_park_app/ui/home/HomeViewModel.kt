package br.com.inove_park_app.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.inove_park_app.data.maps.MapsRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class HomeViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

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

    fun direction(it: Marker?) {
        viewModelScope.launch {
            val s = mapsRepository.direction(markerList[0].position, markerList[1].position)
            Log.e("",s)
        }
    }
}