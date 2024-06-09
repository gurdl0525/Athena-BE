package com.dsm.athena.domain.thesis.service

import com.dsm.athena.domain.thesis.dto.response.ThesisNowResponse
import com.dsm.athena.domain.thesis.repository.ThesisNativeRepository
import com.dsm.athena.domain.thesis.repository.ThesisRepository
import com.dsm.athena.global.error.exception.AthenaException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
internal class ThesisServiceImpl(
    private val thesisRepository: ThesisRepository,
    private val thesisNativeRepository: ThesisNativeRepository
): ThesisService {

    override fun getNow(cursor: Long): ThesisNowResponse {
        val latest = thesisNativeRepository.findByCursor(cursor)
            ?: throw AthenaException(HttpStatus.NO_CONTENT, "There is no content for cursor $cursor")

        return ThesisNowResponse(
            latest.id!!,
            latest.content
        )
    }
}