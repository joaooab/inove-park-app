package br.com.inove_park_app.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentHostCallback
import br.com.inove_park_app.data.Park
import br.com.inove_park_app.ui.ParkWindowForm
import com.google.android.gms.maps.model.Marker

class InfoWindowFactory {

    fun obterInfoWindowFragment(marker: Marker, callback: () -> Unit): Fragment? {
        when (marker.tag) {
            is Park -> {
                return ParkWindowForm.getInstance(marker.tag as Park, callback)
//                val fragment = ParkWindowForm()
//                val args = Bundle()
//                args.putParcelable("tag", marker.tag as GeoClientes)
//                fragment.arguments = args
//                return fragment
            }
            else -> return null
        }
    }
}