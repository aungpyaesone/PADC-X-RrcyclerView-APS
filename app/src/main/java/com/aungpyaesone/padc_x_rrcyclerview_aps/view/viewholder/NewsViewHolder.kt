package com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class NewsViewHolder(itemView: View,delegate: NewsItemDelegate) : CustomBaseViewHolder<NewsVO>(itemView) {
    override fun bindData(data: NewsVO) {
        mData=data
        Glide
            .with(itemView.context)
            .load(data.heroImage)
            .centerCrop()
            .into(itemView.imgView)
        itemView.description.text = data.title

    }

    init {
        itemView.setOnClickListener{

            mData?.let {news->
                delegate.onTouchNewsItem(news.id)
            }

            delegate.touchLike()

        }
    }
}