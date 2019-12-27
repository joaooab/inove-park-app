package br.com.inove_park_app.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import br.com.inove_park_app.R
import br.com.inove_park_app.data.Transfer
import kotlinx.android.synthetic.main.fragment_wallet.*
import java.util.*

class WalletFragment : Fragment() {

    private lateinit var walletViewModel: WalletViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        textViewAddBalance.setOnClickListener {

        }
        recyclerView.adapter = WalletAdapter(
            mutableListOf(
                Transfer(balance = 10.0, date = calendar),
                Transfer(balance = 20.0, date = calendar),
                Transfer(balance = 30.0, date = calendar)
            )
        )
    }
}
