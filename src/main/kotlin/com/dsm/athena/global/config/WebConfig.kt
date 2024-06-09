package com.dsm.athena.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD")
            .allowedHeaders(
                "Authorization",
                "Content-Type",
                "Content-Length",
                "X-Requested-With",
                "Accept",
                "Accept-Encoding",
                "Accept-Language",
                "Accept-Headers",
                "User-Agent",
                "Host",
                "Connection",
            )
            .exposedHeaders("Authorization", "Location")
            .allowCredentials(true)
            .maxAge(3600)
    }
}