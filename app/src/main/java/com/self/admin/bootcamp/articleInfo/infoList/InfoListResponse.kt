package com.self.admin.bootcamp.articleInfo.infoList

import com.self.admin.bootcamp.articleInfo.api.ArticleInfoResponse

data class InfoListResponse(
    val response: Response
)

class Response(
    val status: String,
    val currentPage: Int,
    val pages: Int,
    val results: List<ArticleInfoResponse>
)