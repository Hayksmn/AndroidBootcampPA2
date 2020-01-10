package com.self.admin.bootcamp.articleInfo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleInfoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createArticleInfo(articleInfo: ArticleInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMultipleArticleInfo(articleInfoList: List<ArticleInfo>)

    @Query("SELECT * FROM article_info")
    fun getArticleInfoList(): LiveData<List<ArticleInfo>>

    @Query("SELECT * FROM article_info WHERE article_id=:id")
    fun getArticleInfoById(id: String): LiveData<ArticleInfo>

    @Update
    fun updateArticleInfo(articleInfo: ArticleInfo)

    @Update
    fun updateMultipleArticleInfo(articleInfoList: List<ArticleInfo>)

    @Delete
    fun deleteArticleInfo(articleInfo: ArticleInfo)

    @Delete
    fun deleteMultipleArticleInfo(articleInfoList: List<ArticleInfo>)

    @Query("DELETE FROM article_info")
    fun deleteAll()

}