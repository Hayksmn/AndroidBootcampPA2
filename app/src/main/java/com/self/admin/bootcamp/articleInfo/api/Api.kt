package com.self.admin.bootcamp.articleInfo.api

import retrofit2.Retrofit

class Api {
    lateinit var retrofit: Retrofit

    fun <T> createService(clazz: Class<T>): T = retrofit.create(clazz)
}