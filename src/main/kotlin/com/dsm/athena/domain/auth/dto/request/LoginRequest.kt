package com.dsm.athena.domain.auth.dto.request

import javax.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "null일 수 없습니다.")
    val id: String?,

    @field:NotBlank(message = "null일 수 없습니다.")
    val password: String?,
)
