package com.dsm.athena.global.aws.env

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(value = "cloud.aws.s3")
data class S3Properties(
    val bucket: String,
    val dir: String,
    val accessKey: String,
    val secretKey: String,
    val region: String
)
