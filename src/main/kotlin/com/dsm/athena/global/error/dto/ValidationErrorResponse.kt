package com.dsm.athena.global.error.dto

import org.springframework.http.HttpStatus

data class ValidationErrorResponse(
    val status: HttpStatus,
    val fieldError: List<Map<String, String?>>
)