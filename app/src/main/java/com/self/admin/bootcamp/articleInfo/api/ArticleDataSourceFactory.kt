package com.self.admin.bootcamp.articleInfo.api

import androidx.paging.DataSource
import com.self.admin.bootcamp.articleInfo.infoList.InfoListItem


class ArticleDataSourceFactory(
    articleService: ArticleService
) : DataSource.Factory<Int, InfoListItem>() {

    private val articleDataSource = ArticleDataSource(articleService)


    override fun create(): DataSource<Int, InfoListItem> = articleDataSource
}