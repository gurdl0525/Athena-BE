package com.dsm.athena.global.jwt.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash
data class RefreshToken (

    @Id
    var token: String,

    @Indexed
    var sub: String,

    @TimeToLive
    var timeToLive: Long
)