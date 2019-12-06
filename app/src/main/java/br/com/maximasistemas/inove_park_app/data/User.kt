package br.com.maximasistemas.inove_park_app.data

import java.time.LocalDate

data class User(val login: Login, val name: String, val birthday: LocalDate)