package com.aungpyaesone.padc_x_rrcyclerview_aps.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.CustomBaseViewHolder

abstract class BaseRecyclerAdapter<T: CustomBaseViewHolder<W>,W>
    : RecyclerView.Adapter<T>() {

    //T is view holder
    //W is data type
    var mData:MutableList<W> = mutableListOf()

    override fun getItemCount(): Int {
        return mData.size

    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setData(data:List<W>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun appendNewData(data:W){
        mData.add(data)
        notifyDataSetChanged()
    }
}