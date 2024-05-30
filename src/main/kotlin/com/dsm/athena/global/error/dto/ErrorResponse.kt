package com.dsm.athena.global.error.dto

import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

data class ErrorResponse(
    val status: HttpStatus,
    val message: String
) {
    companion object {

        fun of(status: HttpStatus, message: String) = ErrorResponse(
            status = status,
            message = message
        )

        fun of(e: BindException): ValidationErrorResponse {

            val errorMap = HashMap<String, String?>()

            for (error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ValidationErrorResponse(HttpStatus.BAD_REQUEST, listOf(errorMap))
        }

        fun of(e: ConstraintViolationException): ValidationErrorResponse {

            val errorMap = HashMap<String, String?>()

            for (error: ConstraintViolation<*> in e.constraintViolations) {
                errorMap[error.propertyPath.toString().split('.').last()] = error.message
            }

            return ValidationErrorResponse(HttpStatus.BAD_REQUEST, listOf(errorMap))
        }
    }
}
