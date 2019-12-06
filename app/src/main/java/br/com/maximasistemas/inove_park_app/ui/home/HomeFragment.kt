package br.com.maximasistemas.inove_park_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.maximasistemas.inove_park_app.R
import br.com.maximasistemas.inove_park_app.extension.USER_PREFERENCES
import br.com.maximasistemas.inove_park_app.extension.getStringPreferences

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

    override fun onResume() {
        super.onResume()
        val user = context?.getStringPreferences(USER_PREFERENCES)
        if (user == null) {
        }
    }
}

//val direction = MapFragmentDirections.actionMapFragmentToMapaClienteFragment(
//    rastroInfoVenda,
//    data!!
//)
//view.findNavController().navigate(direction)