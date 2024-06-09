package com.dsm.athena.domain.article.controller

import com.dsm.athena.domain.article.dto.response.ArticleListResponse
import com.dsm.athena.domain.article.service.ArticleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Validated
@RestController
internal class ArticleController(
    private val articleService: ArticleService
) {

    @GetMapping("/articles/latest/{thesis_id}")
    fun getLatestArticles(
        @Valid
        @Positive(message = "양수여야 합니다.")
        @NotNull(message = "null일 수 없습니다.")
        @PathVariable("thesis_id")
        thesisId: Long?,
        @Valid
        @PositiveOrZero(message = "음수일 수 없습니다.")
        @NotNull(message = "null일 수 없습니다.")
        @RequestParam("idx", required = false)
        idx: Int?,
    ): ArticleListResponse = articleService.getLatestArticlesByThesisId(thesisId!!, idx!!)

    @GetMapping("/articles/random/{thesis_id}")
    fun getRandomArticles(
        @Valid
        @Positive(message = "양수여야 합니다.")
        @NotNull(message = "null일 수 없습니다.")
        @PathVariable("thesis_id")
        thesisId: Long?,
        @Valid
        @PositiveOrZero(message = "음수일 수 없습니다.")
        @NotNull(message = "null일 수 없습니다.")
        @RequestParam("idx", required = false)
        idx: Int?,
    ): ArticleListResponse = articleService.getRandomArticlesByThesisId(thesisId!!, idx!!)
}