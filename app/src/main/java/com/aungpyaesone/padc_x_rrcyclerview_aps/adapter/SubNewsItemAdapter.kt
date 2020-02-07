package com.aungpyaesone.padc_x_rrcyclerview_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.SubItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.SubItemViewHolder

class SubNewsItemAdapter(delegate: SubItemDelegate) : RecyclerView.Adapter<SubItemViewHolder>() {
   val mDelegate:SubItemDelegate = delegate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_item_view,parent,false)
        return SubItemViewHolder(view,mDelegate)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: SubItemViewHolder, position: Int) {

    }
}