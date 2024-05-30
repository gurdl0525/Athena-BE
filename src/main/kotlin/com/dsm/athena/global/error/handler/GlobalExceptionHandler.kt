package com.dsm.athena.global.error.handler

import com.dsm.athena.global.error.dto.ErrorResponse
import com.dsm.athena.global.error.dto.ValidationErrorResponse
import com.dsm.athena.global.error.exception.AthenaException
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BindException::class)
    protected fun handleValidationException(e: BindException): ValidationErrorResponse = ErrorResponse.of(e)

    @ExceptionHandler(AthenaException::class)
    protected fun customExceptionHandle(e: AthenaException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e.status, e.message), e.status)

    @ExceptionHandler(ConstraintViolationException::class)
    protected fun handleValidationException(e: ConstraintViolationException): ValidationErrorResponse = ErrorResponse.of(e)
}