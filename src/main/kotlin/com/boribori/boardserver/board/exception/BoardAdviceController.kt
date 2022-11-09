package com.boribori.boardserver.board.exception

import com.boribori.boardserver.common.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BoardAdviceController {

    @ExceptionHandler
    fun handleNotFoundBoardException(ex : NotFoundBoardException) : ResponseEntity<Response<Any>> {
        return ResponseEntity(
                Response(
                        Response.Status(
                                message = "해당 게시물을 찾을 수 없습니다."
                        )
                , null
                ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler
    fun handleNotFoundBookException(ex : NotFoundBookException) : ResponseEntity<Response<Any>>{
        return ResponseEntity(
                Response(
                        Response.Status(
                                message = "해당 책을 찾을 수 없습니다."
                        )
                        , null
                ), HttpStatus.BAD_REQUEST
        )
    }
}