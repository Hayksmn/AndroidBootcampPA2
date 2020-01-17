package com.self.admin.bootcamp

import com.bumptech.glide.Glide
import com.self.admin.bootcamp.articleInfo.api.ArticleService
import com.self.admin.bootcamp.articleInfo.database.ArticleRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {

    single { Glide.with(androidContext()) }

    single {
        Retrofit.Builder()
            .baseUrl("https://content.guardianapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ArticleService::class.java)
    }

    single {
        ArticleRoomDatabase
            .getInstance(androidContext())
            .articleInfoDao
    }
}