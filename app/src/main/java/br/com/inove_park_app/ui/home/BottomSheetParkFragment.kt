package br.com.inove_park_app.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.NumberPicker
import android.widget.RadioGroup
import br.com.inove_park_app.R
import cn.iwgang.countdownview.CountdownView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetParkFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = BottomSheetParkFragment()
    }

    private var mBehavior: BottomSheetBehavior<*>? = null
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialogView = View.inflate(context, R.layout.dialog_bottom_sheet_park, null)
        dialog.setContentView(dialogView)
        setUpNumberPicker()
        setUpRadioGroup()
        setUpButtonParkBegin()
        mBehavior = BottomSheetBehavior.from(dialogView.parent as View)
        return dialog
    }

    private fun setUpButtonParkBegin() {
        val layoutChronometer = dialogView.findViewById<View>(R.id.layout_chronometer)
        val chronometer = dialogView.findViewById<CountdownView>(R.id.chronometer)
        val layoutNumberPicker = dialogView.findViewById<View>(R.id.layout_numberPicker)
        val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        dialogView.findViewById<Button>(R.id.buttonParkBegin).setOnClickListener {
            if (layoutChronometer.visibility == View.INVISIBLE) {
                layoutNumberPicker.visibility = View.INVISIBLE
                layoutChronometer.visibility = View.VISIBLE
                val value = numberPicker.value
                chronometer.start(3600000 * value.toLong())
            } else {
                layoutChronometer.visibility = View.INVISIBLE
                layoutNumberPicker.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpRadioGroup() {
        val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        dialogView.findViewById<RadioGroup>(R.id.radioGroup)
            .setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.radioLongTime) {
                    numberPicker.value = 0
                    numberPicker.isEnabled = false
                } else {
                    numberPicker.isEnabled = true
                }
            }
    }

    private fun setUpNumberPicker() {
        dialogView.findViewById<NumberPicker>(R.id.numberPicker).apply {
            minValue = 1
            maxValue = 24
        }
    }

}