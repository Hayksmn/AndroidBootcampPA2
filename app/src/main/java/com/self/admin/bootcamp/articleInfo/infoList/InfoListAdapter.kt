package com.self.admin.bootcamp.articleInfo.infoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.self.admin.bootcamp.R

class InfoListAdapter(
    private val imageLoader: RequestManager,
    private val articleInfoListener: ArticleInfoListener
) : PagedListAdapter<InfoListItem, BaseViewHolder>(
    DIFF_CALLBACK
) {



    //WARNING, not all article types are supported
    private val itemTypes = mapOf(
        TYPE_OTHER to 0,
        TYPE_ARTICLE to 1,
        TYPE_LIVEBLOG to 2
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            itemTypes.getValue(TYPE_ARTICLE) -> {
                return InfoListItemViewHolderArticle(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.info_list_item_article, parent, false
                    ),
                    imageLoader,
                    articleInfoListener
                )
            }
            itemTypes.getValue(TYPE_LIVEBLOG) -> {
                return InfoListItemViewHolderLiveblog(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.info_list_item_liveblog, parent, false
                    ),
                    imageLoader,
                    articleInfoListener
                )
            }
            else -> {
                return InfoListItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.info_list_item, parent, false
                    ),
                    imageLoader,
                    articleInfoListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.onBind(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val listItem = getItem(position)
        listItem?.run {
            return when (listItem.type) {
                TYPE_ARTICLE -> itemTypes.getValue(TYPE_ARTICLE)
                TYPE_LIVEBLOG -> itemTypes.getValue(TYPE_LIVEBLOG)
                else -> itemTypes.getValue(TYPE_OTHER)
            }
        }
        return 0
    }


    companion object {
        private const val TYPE_ARTICLE = "other"
        private const val TYPE_LIVEBLOG = "article"
        private const val TYPE_OTHER = "liveblog"

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<InfoListItem> =
            object : DiffUtil.ItemCallback<InfoListItem>() {
                override fun areItemsTheSame(
                    oldItem: InfoListItem,
                    newItem: InfoListItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: InfoListItem,
                    newItem: InfoListItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }
}
