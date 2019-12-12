package br.com.inove_park_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.inove_park_app.R
import br.com.inove_park_app.extension.supportFragmentManager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMap()
        buttonPark.setOnClickListener {
            supportFragmentManager {
                BottomSheetParkFragment
                    .newInstance()
                    .show(this, "")
            }
        }
    }

    private fun setUpMap() {
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