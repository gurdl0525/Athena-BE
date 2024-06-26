package com.dsm.athena.global.jwt

import com.dsm.athena.domain.auth.dto.response.TokenResponse
import com.dsm.athena.global.error.exception.AthenaException
import com.dsm.athena.global.jwt.dao.RefreshTokenRepository
import com.dsm.athena.global.jwt.entity.RefreshToken
import com.dsm.athena.global.jwt.env.JwtProperties
import com.dsm.athena.global.security.principal.CustomUserDetailService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class TokenProvider(
    private val property: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authDetailsService: CustomUserDetailService
) {

    private fun generateAccessToken(sub: String): String {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setSubject(sub)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time.plus(property.accessExp)))
            .compact()
    }

    private fun generateRefreshToken(sub: String): String {
        refreshTokenRepository.findBySub(sub)?.let {
            refreshTokenRepository.delete(it)
        }

        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, property.secretKey)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time.plus(property.refreshExp)))
            .compact()

        refreshTokenRepository.save(RefreshToken(token, sub, property.refreshExp))

        return token
    }

    fun receiveToken(sub: String) = TokenResponse (
        generateAccessToken(sub),
        getExp(property.accessExp),
        generateRefreshToken(sub),
        getExp(property.refreshExp)
    )

    private fun getExp(exp: Long) = LocalDateTime.now().withNano(0).plusSeconds(exp / 1000)

    private fun getSubject(token: String): String {
        return try {
            Jwts.parser()
                .setSigningKey(property.secretKey)
                .parseClaimsJws(token).body.subject
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw AthenaException(HttpStatus.BAD_REQUEST, "Token has expired.")
                else -> throw AthenaException(HttpStatus.BAD_REQUEST, "Token has invalid.")
            }
        }
    }

    fun getAuthentication(token: String): Authentication {

        val subject = getSubject(token)

        val authDetails = authDetailsService.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(authDetails, "", authDetails.authorities)
    }

    fun reissue(token: String): TokenResponse{
        val rf = refreshTokenRepository.findByIdOrNull(token)
            ?: throw AthenaException(HttpStatus.BAD_REQUEST, "Token has invalid.")

        return receiveToken(rf.sub)
    }

}
