package br.com.maximasistemas.inove_park_app.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.maximasistemas.inove_park_app.R
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() {

    private lateinit var mapViewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapaVendasInfoWindowFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapaVendasInfoWindowFragment.getMapAsync { map ->
            map.addMarker(
                MarkerOptions().position(
                    LatLng(
                        -25.443150,
                        -49.238243
                    )
                ).title("Jardim Botânico")
            )
        }
    }

}