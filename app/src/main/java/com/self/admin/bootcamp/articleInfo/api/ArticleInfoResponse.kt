package com.self.admin.bootcamp.articleInfo.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class ArticleInfoResponse(
    val id: String,
    val type: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    private val fields: Fields
){
    val thumbnail: String
        get() = fields.thumbnail
}

class Fields(val thumbnail: String)