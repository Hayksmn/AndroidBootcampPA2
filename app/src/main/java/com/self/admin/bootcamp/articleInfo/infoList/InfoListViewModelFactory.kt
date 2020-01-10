package com.self.admin.bootcamp.articleInfo.infoList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.self.admin.bootcamp.articleInfo.api.ArticleRepository
import com.self.admin.bootcamp.articleInfo.api.ArticleService
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao

class InfoListViewModelFactory(
    private val dataSource: ArticleInfoDao,
    private val articleRepository: ArticleRepository
) : ViewModelProvider.Factory {


    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoListViewModel::class.java)) {
            return InfoListViewModel(dataSource, articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}