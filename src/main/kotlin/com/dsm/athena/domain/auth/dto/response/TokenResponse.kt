package com.dsm.athena.domain.auth.dto.response

import java.time.LocalDateTime

data class TokenResponse(
    val generateAccessToken: String,
    val exp: LocalDateTime,
    val generateRefreshToken: String,
    val exp1: LocalDateTime
)
