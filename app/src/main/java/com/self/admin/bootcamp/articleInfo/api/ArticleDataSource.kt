package com.self.admin.bootcamp.articleInfo.api

import androidx.paging.PageKeyedDataSource
import com.self.admin.bootcamp.articleInfo.infoList.InfoListItem
import com.self.admin.bootcamp.articleInfo.infoList.InfoListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ArticleDataSource(
    private val articleService: ArticleService
) : PageKeyedDataSource<Int, InfoListItem>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, InfoListItem>
    ) {
        launch {
            val result = articleService.getArticleList(1)
            callback.onResult(result.mapToInfoListItem(), 1, result.response.currentPage.inc())

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, InfoListItem>) {
        launch {
            val result = articleService.getArticleList(params.key)
            if (result.response.currentPage == result.response.pages) {
                callback.onResult(emptyList(), result.response.currentPage.inc())
            } else {
                callback.onResult(result.mapToInfoListItem(), result.response.currentPage.inc())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, InfoListItem>) {

    }

    private fun InfoListResponse.mapToInfoListItem(): List<InfoListItem> {
        return response.results.map {
            InfoListItem(it.id, it.webTitle, it.type, it.thumbnail, it.apiUrl)
        }
    }

}