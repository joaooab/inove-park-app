package br.com.inove_park_app.ui.wallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.inove_park_app.R
import br.com.inove_park_app.data.Transfer
import br.com.inove_park_app.extension.format
import br.com.inove_park_app.extension.formatIso8601
import kotlinx.android.synthetic.main.item_transfer.view.*

class WalletAdapter(private val list: MutableList<Transfer> = mutableListOf()) :
    RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_transfer,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transfer = list[position]
        holder.bindView(transfer)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(transfer: Transfer) {
            itemView.textViewType.text = transfer.type
            itemView.textViewValue.text = transfer.balance.format()
            itemView.textViewDate.text = transfer.date.formatIso8601()
        }
    }
}
