package com.self.admin.bootcamp.articleInfo.infoList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.self.admin.bootcamp.R

abstract class BaseViewHolder(
    itemView: View,
    protected val imageLoader: RequestManager,
    private val articleInfoListener: ArticleInfoListener
) :
    RecyclerView.ViewHolder(itemView) {
    open fun onBind(item: InfoListItem){
        itemView.setOnClickListener { articleInfoListener(item.apiUrl) }
    }
}

class InfoListItemViewHolder(
    itemView: View,
    imageLoader: RequestManager,
    articleInfoListener: ArticleInfoListener
) : BaseViewHolder(itemView, imageLoader, articleInfoListener) {

    private val titleTV: TextView = itemView.findViewById(R.id.other_title)
    private val typeTV: TextView = itemView.findViewById(R.id.other_type)
    private val thumbnailIV: ImageView = itemView.findViewById(R.id.other_thumbnail)

    override fun onBind(item: InfoListItem) {
        super.onBind(item)
        titleTV.text = item.title
        typeTV.text = typeTV.context.getString(R.string.category, item.type)
        imageLoader.load(item.thumbnail).placeholder(R.drawable.ic_book)
            .into(thumbnailIV)
    }
}

class InfoListItemViewHolderLiveblog(
    itemView: View,
    imageLoader: RequestManager,
    articleInfoListener: ArticleInfoListener
) : BaseViewHolder(itemView, imageLoader, articleInfoListener) {
    private val titleTV: TextView = itemView.findViewById(R.id.liveblog_title)
    private val typeTV: TextView = itemView.findViewById(R.id.liveblog_type)
    private val thumbnailIV: ImageView = itemView.findViewById(R.id.liveblog_thumbnail)

    override fun onBind(item: InfoListItem) {
        super.onBind(item)
        titleTV.text = item.title
        typeTV.text = typeTV.context.getString(R.string.category, item.type)
        imageLoader.load(item.thumbnail).placeholder(R.drawable.ic_book)
            .into(thumbnailIV)
    }
}

class InfoListItemViewHolderArticle(
    itemView: View,
    imageLoader: RequestManager,
    articleInfoListener: ArticleInfoListener
) : BaseViewHolder(itemView, imageLoader, articleInfoListener) {
    private val titleTV: TextView = itemView.findViewById(R.id.article_title)
    private val typeTV: TextView = itemView.findViewById(R.id.article_type)
    private val thumbnailIV: ImageView = itemView.findViewById(R.id.article_thumbnail)

    override fun onBind(item: InfoListItem) {
        super.onBind(item)
        titleTV.text = item.title
        typeTV.text = typeTV.context.getString(R.string.category, item.type)
        imageLoader.load(item.thumbnail).placeholder(R.drawable.ic_book)
            .into(thumbnailIV)
    }
}