package com.dsm.athena.domain.auth.service

import com.dsm.athena.domain.auth.dto.request.LoginRequest
import com.dsm.athena.domain.auth.dto.request.SignUpRequest
import com.dsm.athena.domain.auth.dto.response.TokenResponse

interface AuthService {
    fun signUp(dto: SignUpRequest)
    fun login(dto: LoginRequest): TokenResponse
}