package com.dsm.athena.domain.article.service

import com.dsm.athena.domain.article.dto.response.ArticleListResponse

interface ArticleService {
    fun getLatestArticlesByThesisId(thesisId: Long, idx: Int): ArticleListResponse

    fun getRandomArticlesByThesisId(thesisId: Long, idx: Int): ArticleListResponse
}