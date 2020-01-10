package com.self.admin.bootcamp.articleInfo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_info")
data class ArticleInfo(
    @PrimaryKey @ColumnInfo(name = "article_id") val id: String,
    @ColumnInfo(name = "article_type") val type: String,
    @ColumnInfo(name = "article_web_title") val webTitle: String,
    @ColumnInfo(name = "article_web_url") val webUrl: String,
    @ColumnInfo(name = "article_api_url") val apiUrl: String,
    @ColumnInfo(name = "thumbnail_url") val thumbnail: String,
    @ColumnInfo(name = "is_liked") var isLiked: Boolean = false
)