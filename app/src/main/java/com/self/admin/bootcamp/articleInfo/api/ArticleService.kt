package com.self.admin.bootcamp.articleInfo.api

import com.self.admin.bootcamp.articleInfo.infoList.InfoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("search?q=12%20years%20a%20slave&format=json&tag=film/film,tone/reviews&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=relevance&api-key=da3e84a8-a10c-403d-9817-863e081ee713")
    suspend fun getArticleList(@Query("page") page: Int): InfoListResponse
}