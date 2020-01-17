package com.self.admin.bootcamp.articleInfo.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.self.admin.bootcamp.articleInfo.api.Article
import com.self.admin.bootcamp.articleInfo.api.ArticleContent
import com.self.admin.bootcamp.articleInfo.api.ArticleRepository
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao
import kotlinx.coroutines.*

class ArticleViewModel(
    private val database: ArticleInfoDao,
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _article = MutableLiveData<ArticleContent>()

    val article: LiveData<ArticleContent> = _article

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun fetchArticle(url: String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                _article.postValue(articleRepository.getArticle(url))
            }
        }
    }
}