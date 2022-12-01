package com.boribori.boardserver.reply.exception.advice

import com.boribori.boardserver.board.exception.NotFoundBookException
import com.boribori.boardserver.common.Response
import com.boribori.boardserver.reply.exception.NotFoundReplyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ReplyAdviceController {
    @ExceptionHandler
    fun handleNotFoundReplyAdvice(ex : NotFoundReplyException) : ResponseEntity<Response<Any>> {
        return ResponseEntity(
                Response(
                        Response.Status(
                                message = "해당 대댓글을 찾을 수 없습니다."
                        )
                        , null
                ), HttpStatus.BAD_REQUEST
        )
    }
}