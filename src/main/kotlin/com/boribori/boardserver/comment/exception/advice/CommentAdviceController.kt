package com.boribori.boardserver.comment.exception.advice

import com.boribori.boardserver.comment.exception.NotFoundCommentException
import com.boribori.boardserver.common.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CommentAdviceController {
    @ExceptionHandler
    fun handleNotFoundReplyAdvice(ex : NotFoundCommentException) : ResponseEntity<Response<Any>> {
        return ResponseEntity(
                Response(
                        Response.Status(
                                message = "해당 댓글을 찾을 수 없습니다."
                        )
                        , null
                ), HttpStatus.BAD_REQUEST
        )
    }
}