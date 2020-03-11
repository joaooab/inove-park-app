package br.com.inove_park_app.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.inove_park_app.R
import br.com.inove_park_app.data.TransferMemory
import br.com.inove_park_app.data.Transfer
import br.com.inove_park_app.extension.format
import br.com.inove_park_app.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_wallet.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class WalletFragment : Fragment() {

    private val viewModel: WalletViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeWallet()
        observeTransfers()
        setUpAddBalance()
    }

    private fun observeTransfers() {
        viewModel.transfers.observe(this, Observer {
            recyclerView.adapter = WalletAdapter(it)
            viewModel.calculateTotalBalance()
        })
    }

    private fun setUpAddBalance() {
        textViewAddBalance.setOnClickListener {
            viewModel.addTransfer(Transfer(balance = 10.0, date = Calendar.getInstance()))
        }
    }

    private fun observeWallet() {
        viewModel.wallet.observe(this, Observer {
            textViewBalance.text = it.balance.format()
        })
    }

}
