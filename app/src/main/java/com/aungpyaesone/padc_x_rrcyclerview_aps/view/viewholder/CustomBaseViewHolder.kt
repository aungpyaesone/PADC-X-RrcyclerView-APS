package com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mData : T? = null

    abstract fun bindData(data:T)
}