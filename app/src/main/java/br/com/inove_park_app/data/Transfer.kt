package br.com.inove_park_app.data

import java.util.*

data class Transfer(val type: String = "Recarga", val balance: Double, val date: Calendar)
