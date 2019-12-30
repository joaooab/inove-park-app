package br.com.inove_park_app.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.inove_park_app.R
import br.com.inove_park_app.extension.format
import br.com.inove_park_app.ui.home.BottomSheetParkViewModel.Companion.MAX_VALUE
import br.com.inove_park_app.ui.home.BottomSheetParkViewModel.Companion.MIN_VALUE
import br.com.inove_park_app.util.LayoutUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetParkFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = BottomSheetParkFragment()
    }

    private lateinit var viewModel: BottomSheetParkViewModel
    private var mBehavior: BottomSheetBehavior<*>? = null
    private lateinit var dialogView: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        viewModel = ViewModelProviders.of(this).get(BottomSheetParkViewModel::class.java)
        dialogView = View.inflate(context, R.layout.dialog_bottom_sheet_park, null)
        dialog.setContentView(dialogView)
        setUpNumberPicker()
        setUpRadioGroup()
        setUpButtonParkBegin()
        observeCronometer()
        observeStarted()
        observeBalance()
        observeTotal()
        observeCost()
        mBehavior = BottomSheetBehavior.from(dialogView.parent as View)
        return dialog
    }

    private fun observeCost() {
        val textView = dialogView.findViewById<TextView>(R.id.textViewCost)
        viewModel.cost.observe(this, Observer {
            textView.text = it.format()
        })
    }

    private fun observeTotal() {
        val textView = dialogView.findViewById<TextView>(R.id.textViewTotal)
        viewModel.total.observe(this, Observer {
            textView.text = it.format()
        })
    }

    private fun observeBalance() {
        val textView = dialogView.findViewById<TextView>(R.id.textViewBalance)
        viewModel.balance.observe(this, Observer {
            viewModel.calculateTotal()
            textView.text = it.format()
        })
    }

    private fun observeStarted() {
        val layoutChronometer = dialogView.findViewById<View>(R.id.layout_chronometer)
        val layoutNumberPicker = dialogView.findViewById<View>(R.id.layout_numberPicker)
        val button = dialogView.findViewById<Button>(R.id.buttonParkBegin)
        viewModel.started.observe(this, Observer {
            if (it) {
                layoutNumberPicker.visibility = View.GONE
                layoutChronometer.visibility = View.VISIBLE
                button.text = LayoutUtil.getString(R.string.finish)
                button.backgroundTintList = LayoutUtil.getColorStateList(R.color.green_dark)
            } else {
                layoutNumberPicker.visibility = View.VISIBLE
                layoutChronometer.visibility = View.GONE
                button.text = LayoutUtil.getString(R.string.begin)
                button.backgroundTintList = LayoutUtil.getColorStateList(R.color.colorAccent)
            }
        })
    }

    private fun observeCronometer() {
        val cronometer = dialogView.findViewById<TextView>(R.id.chronometer)
        viewModel.cronometer.observe(this, Observer {
            cronometer.text = it
        })
    }

    private fun setUpButtonParkBegin() {
        val button = dialogView.findViewById<Button>(R.id.buttonParkBegin)
        button.setOnClickListener {
            if (viewModel.isStarted()) {
                viewModel.shutDown()
            } else {
                val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
                viewModel.start(numberPicker.value)
            }
        }
    }

    private fun setUpRadioGroup() {
        val numberPicker = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        dialogView.findViewById<RadioGroup>(R.id.radioGroup)
            .setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.radioLongTime) {
                    numberPicker.value = MAX_VALUE
                    numberPicker.isEnabled = false
                } else {
                    numberPicker.isEnabled = true
                    numberPicker.value = MIN_VALUE
                }
            }
    }

    private fun setUpNumberPicker() {
        dialogView.findViewById<NumberPicker>(R.id.numberPicker).apply {
            minValue = MIN_VALUE
            maxValue = MAX_VALUE
        }.setOnValueChangedListener { _, _, newVal ->
            viewModel.changeCost(newVal)
        }
    }

}