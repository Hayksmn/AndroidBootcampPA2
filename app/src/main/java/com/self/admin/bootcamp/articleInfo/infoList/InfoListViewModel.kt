package com.self.admin.bootcamp.articleInfo.infoList

import androidx.lifecycle.*
import com.self.admin.bootcamp.articleInfo.database.ArticleInfo
import com.self.admin.bootcamp.articleInfo.database.ArticleInfoDao
import kotlinx.coroutines.*

class InfoListViewModel(private val database: ArticleInfoDao) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _articleInfoList = MutableLiveData<List<ArticleInfo>>()
    val articleInfoList: LiveData<List<ArticleInfo>>
        get() = _articleInfoList


    public fun getListFromDb() = uiScope.launch {
        withContext(Dispatchers.IO) {
            Transformations.map(database.getArticleInfoList()) {
                _articleInfoList.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}