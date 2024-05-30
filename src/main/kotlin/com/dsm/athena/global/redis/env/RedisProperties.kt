package com.dsm.athena.global.redis.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "spring.redis")
data class RedisProperties(
    val host: String,
    val port: Int
)
