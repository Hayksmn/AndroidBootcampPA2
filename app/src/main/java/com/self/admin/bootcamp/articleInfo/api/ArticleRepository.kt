package com.self.admin.bootcamp.articleInfo.api

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.self.admin.bootcamp.articleInfo.infoList.InfoListItem

interface ArticleRepository {
    fun getArticles(): ArticleList

    suspend fun getArticle(url: String): ArticleContent
}


class ArticleRepositoryImpl(private val articleService: ArticleService) : ArticleRepository {

    private val articleDataSourceFactory = ArticleDataSourceFactory(articleService)

    override fun getArticles(): ArticleList =
        ArticleList(LivePagedListBuilder(articleDataSourceFactory, 1).build())

    override suspend fun getArticle(url: String): ArticleContent =
        articleService.getArticle(url).response.content
}


class ArticleList(val data: LiveData<PagedList<InfoListItem>>)