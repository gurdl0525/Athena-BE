package com.dsm.athena.global.jwt.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties (
    val secretKey: String,
    val accessExp: Long,
    val refreshExp: Long
)