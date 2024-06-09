package com.dsm.athena.domain.thesis.service

import com.dsm.athena.domain.thesis.dto.response.ThesisNowResponse

interface ThesisService {

    fun getNow(cursor: Long): ThesisNowResponse
}