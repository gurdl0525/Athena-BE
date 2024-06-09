package com.dsm.athena.domain.article.repository

import com.dsm.athena.domain.article.entity.Article
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

@org.springframework.stereotype.Repository
interface ArticleNativeRepository: Repository<Article, Long?> {

    @Query(
        "SELECT * FROM tbl_article as a " +
                "where a.thesis_id = :thesisId ORDER BY RAND() LIMIT 9 OFFSET :idx",
        nativeQuery = true
    )
    fun findAllByThesisIdAndRandom(@Param("thesisId") thesisId: Long, @Param("idx") idx: Int): List<Article>
}