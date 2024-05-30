package com.dsm.athena.domain.arthicle.repository

import com.dsm.athena.domain.arthicle.entity.Article
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: CrudRepository<Article, Long?> {
}