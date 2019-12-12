package br.com.inove_park_app.extension

import java.text.NumberFormat
import java.util.*

private val formatoMonetario = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

fun Double.format(): String = formatoMonetario.format(this)
