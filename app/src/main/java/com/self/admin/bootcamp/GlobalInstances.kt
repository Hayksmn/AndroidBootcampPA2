package com.self.admin.bootcamp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.self.admin.bootcamp.articleInfo.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object GlobalInstances {

    val api: Api by lazy { Api() }
    lateinit var imageLoader: RequestManager
        private set


    fun initApiClient() {
        api.retrofit = Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun initImageLoader(context: Context){
        imageLoader = Glide.with(context)
    }
}