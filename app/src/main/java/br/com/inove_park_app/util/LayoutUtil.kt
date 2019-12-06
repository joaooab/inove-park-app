package br.com.inove_park_app.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.amulyakhare.textdrawable.TextDrawable
import org.koin.core.KoinComponent
import org.koin.core.inject

object LayoutUtil : KoinComponent {

    private val context: Context by inject()

    fun createCircularShadowLetter(letra: String, cor: Int): TextDrawable {
        return TextDrawable.builder()
            .beginConfig()
            .bold()
            .endConfig()
            .buildRound(letra, cor)
    }

    fun getColor(color: Int) = ContextCompat.getColor(context, color)

    fun getDrawable(drawable: Int) = ContextCompat.getDrawable(context, drawable)

    fun getString(idString: Int): String = context.getString(idString)

}
