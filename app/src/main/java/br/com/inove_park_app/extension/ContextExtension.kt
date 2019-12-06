package br.com.inove_park_app.extension

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val DEFAULT_SHARED_PREFERENCES = "INOVE_PARK_PREFERENCES"
const val USER_PREFERENCES = "USER_PREFERENCES"

private fun Context.getDefaultSharedPreferences() =
    this.getSharedPreferences(DEFAULT_SHARED_PREFERENCES, MODE_PRIVATE)


fun Context.getStringPreferences(key: String, default: String = "") =
    getDefaultSharedPreferences().getString(key, default)

fun Context.putStringPreferences(key: String, value: String) =
    getDefaultSharedPreferences().edit()
        .putString(key, value)
        .apply()

