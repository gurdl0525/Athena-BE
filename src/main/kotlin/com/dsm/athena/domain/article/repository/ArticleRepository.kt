package com.dsm.athena.domain.article.repository

import com.dsm.athena.domain.article.entity.Article
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: CrudRepository<Article, Long?> {
    fun findAllByThesisId(id: Long, pageable: Pageable): List<Article>
}