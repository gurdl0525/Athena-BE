package com.dsm.athena.domain.auth.controller

import com.dsm.athena.domain.auth.dto.request.LoginRequest
import com.dsm.athena.domain.auth.dto.request.SignUpRequest
import com.dsm.athena.domain.auth.dto.response.TokenResponse
import com.dsm.athena.domain.auth.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(
        @Valid
        @RequestBody
        dto: SignUpRequest
    ) {
        authService.signUp(dto)
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(
        @Valid
        @RequestBody
        dto: LoginRequest
    ): TokenResponse = authService.login(dto)
}