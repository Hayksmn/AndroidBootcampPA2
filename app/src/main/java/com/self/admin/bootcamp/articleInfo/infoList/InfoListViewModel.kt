package com.self.admin.bootcamp.articleInfo.infoList

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.self.admin.bootcamp.articleInfo.api.ArticleList
import com.self.admin.bootcamp.articleInfo.api.ArticleRepository
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao
import kotlinx.coroutines.*

class InfoListViewModel(
    private val database: ArticleInfoDao,
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _articleInfoList = MutableLiveData<ArticleList>()

    val articleInfoList: LiveData<PagedList<InfoListItem>> =
        Transformations.switchMap(_articleInfoList) {
            it.data
        }


    fun getListFromDb() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                Transformations.map(database.getArticleInfoList()) {
                    //                    _articleInfoList.postValue(it)
                }
            }
        }
    }

    fun getListFromNetwork() {
        uiScope.launch {
            val articles = articleRepository.getArticles()

            _articleInfoList.postValue(articles)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}