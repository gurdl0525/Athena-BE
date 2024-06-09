package com.dsm.athena.domain.auth.service

import com.dsm.athena.domain.auth.dto.request.LoginRequest
import com.dsm.athena.domain.auth.dto.request.SignUpRequest
import com.dsm.athena.domain.auth.dto.response.TokenResponse
import com.dsm.athena.domain.user.entity.User
import com.dsm.athena.domain.user.repository.UserRepository
import com.dsm.athena.global.error.exception.AthenaException
import com.dsm.athena.global.jwt.TokenProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
internal class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val tokenProvider: TokenProvider
): AuthService {

    override fun signUp(dto: SignUpRequest) {
        userRepository.findByIdOrNull(dto.id!!)?.let{
            throw AthenaException(HttpStatus.BAD_REQUEST, "Duplicate id")
        }

        userRepository.save(User(dto.id, passwordEncoder.encode(dto.password!!)))
    }

    override fun  login(dto: LoginRequest): TokenResponse {
        val user = userRepository.findByIdOrNull(dto.id!!)
            ?: throw AthenaException(HttpStatus.NOT_FOUND, "User not found")

        if (!passwordEncoder.matches(dto.password!!, user.password))
            throw AthenaException(HttpStatus.UNAUTHORIZED, "Invalid password")

        return tokenProvider.receiveToken(user.id)
    }
}