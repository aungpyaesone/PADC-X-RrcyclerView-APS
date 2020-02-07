package com.aungpyaesone.padc_x_rrcyclerview_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.SubItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.NestedMainViewHolder
import kotlinx.android.synthetic.main.nested_main_item_view.view.*

class NestedMainAdapter(delegate:SubItemDelegate) : RecyclerView.Adapter<NestedMainViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()

    val mDelegate:SubItemDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedMainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nested_main_item_view, parent, false)
        return NestedMainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: NestedMainViewHolder, position: Int) {

        val childlayoutManager = LinearLayoutManager(
            holder.itemView.subRecycler.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        holder.itemView.subRecycler.apply {
            layoutManager = childlayoutManager
            adapter = SubNewsItemAdapter(mDelegate)
            setRecycledViewPool(viewPool)
        }


    }
}