package br.com.inove_park_app.ui.component

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import br.com.inove_park_app.extension.formatDataBr
import java.util.*

class EditTextDatePicker(context: Context, attrs: AttributeSet) : EditText(context, attrs) {

    private var data: Calendar? = null

    override fun performClick(): Boolean {
        val myCalendar: Calendar = if (data != null) {
            data as Calendar
        } else {
            Calendar.getInstance()
        }
        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val novoCalendar = Calendar.getInstance()
            novoCalendar.set(Calendar.YEAR, year)
            novoCalendar.set(Calendar.MONTH, monthOfYear)
            novoCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setData(novoCalendar)
        }
        DatePickerDialog(
            context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()

        return super.performClick()
    }

    fun setData(calendar: Calendar?) {
        data = calendar
        if (data == null) {
            setText("")
        } else {
            setText(calendar?.formatDataBr())
        }
    }

    fun getData(): Calendar? {
        return data
    }
}