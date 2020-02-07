package com.aungpyaesone.padc_x_rrcyclerview_aps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.BaseViewViewHolder
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.CustomBaseViewHolder
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.NewsViewHolder
import com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder.SmallViewHolder

class NewsListAdapter(delegate:NewsItemDelegate) : BaseRecyclerAdapter<CustomBaseViewHolder<NewsVO>,NewsVO>(){
    val mDelegate:NewsItemDelegate = delegate // m is called member
    // for viewType

    val VIEW_TYPE_LARGE = 20
    val VIEW_TYPE_SMALL = 30

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomBaseViewHolder<NewsVO> {

        when(viewType){
            VIEW_TYPE_LARGE ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
                return NewsViewHolder(view,mDelegate)
            }
            VIEW_TYPE_SMALL -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_small,parent,false)
                return SmallViewHolder(view,mDelegate)
            }
            else ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
                return NewsViewHolder(view,mDelegate)
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        when{
            position%3 == 0 -> {
                return VIEW_TYPE_LARGE
            }
            else -> {
                return VIEW_TYPE_SMALL
            }
        }
    }

}