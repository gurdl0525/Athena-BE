package com.dsm.athena.domain.article.dto.response

import java.time.LocalDateTime

data class ArticleResponse (
    val id: Long,
    val thumbnail: String?,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val replyCount: Long,
    val likeCount: Long,
    val author: AuthorResponse
)
