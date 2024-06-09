package com.dsm.athena.domain.thesis.controller

import com.dsm.athena.domain.thesis.dto.response.ThesisNowResponse
import com.dsm.athena.domain.thesis.service.ThesisService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@Validated
@RestController
@RequestMapping("/thesis")
internal class ThesisController(
    private val thesisService: ThesisService
) {

    @GetMapping("/now/{cursor}")
    fun getThesisNow(
        @Valid
        @PositiveOrZero(message = "음수일 수 없습니다.")
        @NotNull(message = "null일 수 없습니다.")
        @PathVariable cursor: Long?
    ): ThesisNowResponse = thesisService.getNow(cursor!!)
}