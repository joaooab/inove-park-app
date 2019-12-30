package br.com.inove_park_app.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.inove_park_app.data.TransferMemory
import br.com.inove_park_app.data.Transfer
import br.com.inove_park_app.data.Wallet
import br.com.inove_park_app.data.WalletMemory

class WalletViewModel : ViewModel() {

    private val _wallet: MutableLiveData<Wallet> = MutableLiveData()
    val wallet: LiveData<Wallet> = _wallet

    private val _transfers: MutableLiveData<MutableList<Transfer>> = MutableLiveData()
    val transfers: LiveData<MutableList<Transfer>> = _transfers

    init {
        _transfers.value = TransferMemory.list
        calculateTotalBalance()
    }

    fun addTransfer(transfer: Transfer) {
        _transfers.value?.add(transfer)
        _transfers.postValue(_transfers.value)
    }

    fun calculateTotalBalance() {
        val sum = _transfers.value?.sumByDouble { it.balance } ?: 0.0
        val newWallet = Wallet(sum)
        WalletMemory.wallet = newWallet
        _wallet.postValue(newWallet)
    }

}
