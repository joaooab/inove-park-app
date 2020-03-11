package br.com.inove_park_app.extension

import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT_ISO_8601 = "yyyy-MM-dd HH:mm:ss"
const val DATE_FORMAT_BR = "dd/MM/yyyy"
const val DATE_FORMAT_US = "yyyy-MM-dd"
const val DATE_TIME_FORMAT_BR = "dd/MM/yyyy HH:mm"

fun Calendar.formatDataBr(): String {
    return formatToString(DATE_FORMAT_BR)
}

fun Calendar.formatDiaMesBr(): String {
    val format = "dd/MM"
    return formatToString(format)
}

fun Calendar.formatIso8601(): String {
    return formatToString(DATE_TIME_FORMAT_ISO_8601)
}

fun Calendar.formatToString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale("pt", "BR"))
    return try {
        simpleDateFormat.format(time)
    } catch (e: Exception) {
        ""
    }
}

fun Calendar.primeiroDiaDoMes() =
    this.apply { set(Calendar.DAY_OF_MONTH, this.firstDayOfWeek) }

fun Calendar.ultimoDiaDoMes() =
    this.apply { set(Calendar.DAY_OF_MONTH, this.getActualMaximum(Calendar.DAY_OF_MONTH)) }

fun getSimpleDateFormat(format: String) = SimpleDateFormat(format, Locale("pt", "BR"))

