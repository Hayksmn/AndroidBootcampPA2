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
    private val imageLoader: RequestManager
) : PagedListAdapter<InfoListItem, InfoListAdapter.InfoListItemViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoListItemViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(
            R.layout.info_list_item, parent, false
        )
        return InfoListItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: InfoListItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.titleTV.text = it.title
            holder.typeTV.text = holder.typeTV.context.getString(R.string.category, it.type)
            imageLoader.load(it.thumbnail).placeholder(R.drawable.ic_book).into(holder.thumbnailIV)
        }
    }

    class InfoListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTV: TextView = itemView.findViewById(R.id.title)
        val typeTV: TextView = itemView.findViewById(R.id.type)
        val thumbnailIV: ImageView = itemView.findViewById(R.id.thumbnail)//TODO WIP
    }


    companion object {
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
