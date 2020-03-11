package br.com.inove_park_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.inove_park_app.R
import br.com.inove_park_app.data.Park
import kotlinx.android.synthetic.main.window_form_park.view.*

class ParkWindowForm: Fragment() {

    private lateinit var park: Park
    private lateinit var callback: () -> Unit

    companion object {
        fun getInstance(park: Park, callback: () -> Unit): ParkWindowForm {
            val parkWindowForm = ParkWindowForm()
            parkWindowForm.park = park
            parkWindowForm.callback = callback
            return parkWindowForm
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.window_form_park, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.textViewMarkParkingName.text = park.name
        view.buttonMarkReserve.setOnClickListener {
            callback()
        }

    }
}