package com.self.admin.bootcamp.articleInfo.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.self.admin.bootcamp.articleInfo.api.ArticleRepository
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao

class ArticleViewModelFactory(
    private val dataSource: ArticleInfoDao,
    private val articleRepository: ArticleRepository
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(dataSource, articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}