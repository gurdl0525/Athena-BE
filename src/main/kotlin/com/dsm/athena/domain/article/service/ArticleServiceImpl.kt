package com.dsm.athena.domain.article.service

import com.dsm.athena.domain.article.dto.response.ArticleListResponse
import com.dsm.athena.domain.article.dto.response.ArticleResponse
import com.dsm.athena.domain.article.dto.response.AuthorResponse
import com.dsm.athena.domain.article.repository.ArticleNativeRepository
import com.dsm.athena.domain.article.repository.ArticleRepository
import com.dsm.athena.global.error.exception.AthenaException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
internal class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val articleNativeRepository: ArticleNativeRepository
): ArticleService {

    override fun getLatestArticlesByThesisId(thesisId: Long, idx: Int): ArticleListResponse {
        val articles = articleRepository.findAllByThesisId(
            thesisId,
            PageRequest.of(
                idx,
                9,
                Sort.by(Sort.Direction.DESC, "createdAt")
            )
        )

        if (articles.isEmpty()) throw AthenaException(HttpStatus.NO_CONTENT, "There is no content by thesis : id=$thesisId")

        val responses = articles.map {
            ArticleResponse(
                it.id!!,
                it.thumbnailUrl,
                it.title,
                if (it.content.length < 128) it.content else it.content.substring(0, 128),
                it.createdAt,
                it.commentCount,
                it.likeCount,
                it.writer.run {
                    AuthorResponse(
                        id,
                        profileImage
                    )
                }
            )
        }

        return ArticleListResponse(responses)
    }

    override fun getRandomArticlesByThesisId(thesisId: Long, idx: Int): ArticleListResponse {
        val articles = articleNativeRepository.findAllByThesisIdAndRandom(thesisId, idx)

        if (articles.isEmpty()) throw AthenaException(HttpStatus.NO_CONTENT, "There is no content by thesis : id=$thesisId")

        val responses = articles.map {
            ArticleResponse(
                it.id!!,
                it.thumbnailUrl,
                it.title,
                if (it.content.length < 128) it.content else it.content.substring(0, 128),
                it.createdAt,
                it.commentCount,
                it.likeCount,
                it.writer.run {
                    AuthorResponse(
                        id,
                        profileImage
                    )
                }
            )
        }

        return ArticleListResponse(responses)
    }

}