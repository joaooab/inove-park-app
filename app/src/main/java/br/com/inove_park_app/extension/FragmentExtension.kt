package br.com.inove_park_app.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Fragment.supportFragmentManager(executa: FragmentManager.() -> Unit) {
    val supportFragmentManager = activity?.supportFragmentManager
        ?: throw IllegalArgumentException("Activity não pode ser null")
    executa(supportFragmentManager)
}

fun Fragment.showShortToast(message: String)  {
    context?.let {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.showLongToast(message: String)  {
    context?.let {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}