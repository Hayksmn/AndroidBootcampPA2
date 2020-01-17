package com.self.admin.bootcamp.articleInfo.api

import com.self.admin.bootcamp.articleInfo.infoList.InfoListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ArticleService {



    @GET("search?q=12%20years%20a%20slave&format=json&tag=film/film,tone/reviews&from-date=2010-01-01&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=relevance")
    suspend fun getArticleList(
        @Query("page") page: Int,
        @Query("api-key") apiKey: String = API_KEY
    ): InfoListResponse

    @GET
    suspend fun getArticle(
        @Url url: String,
        @Query("show-fields") showFields: String = "all",
        @Query("api-key") apiKey: String = API_KEY
        ): Article

    companion object{
        private const val API_KEY = "da3e84a8-a10c-403d-9817-863e081ee713"
    }
}