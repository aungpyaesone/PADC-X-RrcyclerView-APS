package com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.SubItemDelegate

class SubItemViewHolder(itemView: View,delegate: SubItemDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener{
            delegate.touchItem()
        }

    }
}