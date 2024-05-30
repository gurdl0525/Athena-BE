package com.dsm.athena.global.jwt.dao

import com.dsm.athena.global.jwt.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: CrudRepository<RefreshToken, String> {
    fun findBySub(subject: String): RefreshToken?
}