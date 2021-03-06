package br.com.inove_park_app.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Park(
    val name: String = ""
) : Parcelable