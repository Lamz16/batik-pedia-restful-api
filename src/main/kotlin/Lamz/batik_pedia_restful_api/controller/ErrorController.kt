package Lamz.batik_pedia_restful_api.controller

import Lamz.batik_pedia_restful_api.error.NotFoundException
import Lamz.batik_pedia_restful_api.model.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(ConstraintViolationException::class)
    fun validationHandler(contraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = contraintViolationException.message!!
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = ex.message!!
        )
    }

}