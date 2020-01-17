package com.self.admin.bootcamp.articleInfo.api

class Article(
    val response: ArticleResponse
)

class ArticleResponse(
    val content: ArticleContent
)

class ArticleContent(
    val id: String,
    val type: String,
    private val fields: ArticleFields
) {
    val headline: String
        get() = fields.headline

    val trailText: String
        get() = fields.trailText

    val byline: String
        get() = fields.byline

    val thumbnail: String
        get() = fields.thumbnail

    val bodyText: String
        get() = fields.bodyText
}

class ArticleFields(
    val headline: String,
    val trailText: String,
    val byline: String,
    val thumbnail: String,
    val bodyText: String
)