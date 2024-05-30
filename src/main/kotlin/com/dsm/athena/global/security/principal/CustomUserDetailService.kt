package com.dsm.athena.global.security.principal

import com.dsm.athena.domain.user.repository.UserRepository
import com.dsm.athena.global.error.exception.AthenaException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): CustomUserDetails =
        userRepository.findByIdOrNull(username!!)?.run { CustomUserDetails(this) }
            ?: throw AthenaException(HttpStatus.UNAUTHORIZED, "Wrong token because not exists token.")
}