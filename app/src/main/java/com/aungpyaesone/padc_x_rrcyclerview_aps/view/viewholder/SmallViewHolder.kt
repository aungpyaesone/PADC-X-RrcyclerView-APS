package com.aungpyaesone.padc_x_rrcyclerview_aps.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aungpyaesone.padc_x_rrcyclerview_aps.data.vos.NewsVO
import com.aungpyaesone.padc_x_rrcyclerview_aps.delegation.NewsItemDelegate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_new_small.view.*
import kotlinx.android.synthetic.main.item_view.view.*

class SmallViewHolder(itemView: View,delegate:NewsItemDelegate) : CustomBaseViewHolder<NewsVO>(itemView) {
    override fun bindData(data: NewsVO) {

        mData = data
        Glide
            .with(itemView.context)
            .load(data.heroImage)
            .centerCrop()
            .into(itemView.ivNewsImage)
        itemView.tvNewsHeadLine?.text = data?.title
        itemView.tvPublishedDate?.text = data.publication?.postedDate

    }


    init{
        itemView.setOnClickListener {
          //  delegate.onTouchNewsItem()
            mData?.let {
                newsVO ->
                delegate.onTouchNewsItem(it.id)
            }
        }
    }
}