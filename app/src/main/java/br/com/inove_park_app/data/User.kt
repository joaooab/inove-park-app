package br.com.inove_park_app.data

import java.util.*

data class User(
    val login: Login = Login(),
    val name: String = "",
    val phone: String = "",
    val birthday: Calendar? = null
)