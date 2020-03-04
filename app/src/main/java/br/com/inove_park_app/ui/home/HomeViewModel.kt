package br.com.inove_park_app.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.inove_park_app.data.google.Direction
import br.com.inove_park_app.data.maps.MapsRepository
import br.com.inove_park_app.util.LatLngUtil
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch

class HomeViewModel(private val mapsRepository: MapsRepository) : ViewModel() {

    private val _route: MutableLiveData<Direction> = MutableLiveData()
    val route: LiveData<Direction> = _route

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

    fun getDirection(
        it: Marker?,
        mLastKnownLocation: Location?
    ) {
        viewModelScope.launch {
            val origin = LatLngUtil.toString(mLastKnownLocation)
            val destination = LatLngUtil.toString(markerList[0].position)
            _route.value = mapsRepository.getDirections(origin, destination)
        }
    }
}