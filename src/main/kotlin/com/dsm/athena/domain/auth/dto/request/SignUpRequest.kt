package com.dsm.athena.domain.auth.dto.request

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotBlank

data class SignUpRequest(
    @field:Length(max = 21, message = "아이디는 21자 이하입니다.")
    @field:NotBlank(message = "null일 수 없습니다.")
    val id: String?,

    @field:NotBlank(message = "null일 수 없습니다.")
    val password: String?,

    @field:Length(max = 21, message = "프로필 사진 링크는 255자 이하입니다.")
    @field:URL(message = "url 형식이여야 합니다.")
    val profileImage: String?,
)
