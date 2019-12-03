package br.com.maximasistemas.inove_park_app.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.supportFragmentManager(executa: FragmentManager.() -> Unit) {
    val supportFragmentManager = activity?.supportFragmentManager
        ?: throw IllegalArgumentException("Activity não pode ser null")
    executa(supportFragmentManager)
}

