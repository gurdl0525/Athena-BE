package com.dsm.athena.global.filter

import com.dsm.athena.global.error.exception.AthenaException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper
): OncePerRequestFilter(){

    private fun sendErrorResponse(
        status: Int,
        message: String,
        response: HttpServletResponse
    ){

        response.let {
            it.status = status
            it.contentType = MediaType.APPLICATION_JSON_VALUE
            it.characterEncoding = "UTF-8"
        }

        objectMapper.writeValue(
            response.writer,
            message
        )
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: AthenaException) {
            e.printStackTrace()
            sendErrorResponse(e.status.value(), e.message, response)
        } catch (e: Exception){
            e.printStackTrace()
            sendErrorResponse(500, "Something went wrong.", response)
        }
    }
}