package com.dsm.athena.global.security.config

import com.dsm.athena.global.filter.ExceptionHandlerFilter
import com.dsm.athena.global.filter.FilterConfig
import com.dsm.athena.global.jwt.JwtTokenResolver
import com.dsm.athena.global.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val exceptionHandlerFilter: ExceptionHandlerFilter,
    private val tokenResolver: JwtTokenResolver,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .formLogin().disable()
            .cors().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()

            .antMatchers("/**").permitAll()
            .anyRequest().permitAll()
            .and()

            .apply(FilterConfig(tokenProvider, tokenResolver, exceptionHandlerFilter))
            .and().build()
    }

    @Bean
    protected fun passwordEncoder() = BCryptPasswordEncoder()
}