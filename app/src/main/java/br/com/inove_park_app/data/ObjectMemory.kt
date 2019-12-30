package br.com.inove_park_app.data

object WalletMemory {

    const val INIT_VALUE = 2.5

    var wallet = Wallet(0.0)
    val cost = INIT_VALUE

}


object TransferMemory {

    val list: MutableList<Transfer> = mutableListOf()

}