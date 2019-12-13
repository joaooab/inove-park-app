package br.com.inove_park_app.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import br.com.inove_park_app.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetParkFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = BottomSheetParkFragment()
    }

    private var mBehavior: BottomSheetBehavior<*>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.dialog_bottom_sheet_park, null)
//        setUpRecyclerView(view)
//        setUpBackPress(view)
//        setUpDone(view)
        dialog.setContentView(view)
        setUpNumberPicker(view)
        mBehavior = BottomSheetBehavior.from(view.parent as View)
        return dialog
    }

    private fun setUpNumberPicker(view: View) {
        view.findViewById<NumberPicker>(R.id.numberPicker).apply {
            minValue = 0
            maxValue = 24
        }
    }

}