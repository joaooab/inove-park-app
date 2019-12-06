package br.com.inove_park_app.util

import android.widget.EditText
import br.com.inove_park_app.R

class ValidatorFieldBuilder {

    private val INVALID_VALUE_MESSAGE = LayoutUtil.getString(R.string.invalid_value)
    private val REQUIRED_FIELD_MESSAGE = LayoutUtil.getString(R.string.required_field)
    private var succes: Boolean = true

    fun requiredField(field: EditText): ValidatorFieldBuilder {
        if (field.text.isNullOrEmpty()) {
            field.error = REQUIRED_FIELD_MESSAGE
            succes = false
        }
        return this
    }

    fun doubleField(field: EditText): ValidatorFieldBuilder {
        try {
            field.text.toString().toDouble()
        } catch (e: Exception) {
            field.error = INVALID_VALUE_MESSAGE
            succes = false
        }
        return this
    }

    fun biggerThan(
        field: EditText,
        value: Double,
        idMessage: Int? = null,
        message: String = INVALID_VALUE_MESSAGE
    ): ValidatorFieldBuilder {
        try {
            val fieldValue = field.text.toString().toDouble()
            if (fieldValue > value) {
                field.error = if (idMessage != null) {
                    LayoutUtil.getString(idMessage)
                } else {
                    message
                }
            }
        } catch (e: Exception) {
            field.error = INVALID_VALUE_MESSAGE
            succes = false
        }
        return this
    }


    fun build(): Boolean = succes
}