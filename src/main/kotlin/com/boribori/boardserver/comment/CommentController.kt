package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.comment.dto.RequestOfCreateComment
import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfCreateComment
import com.boribori.boardserver.common.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController (
        private val commentService: CommentService
        ){

    @GetMapping("/api/board/{boardId}/comment")
    fun getComment(boardId : String, @RequestParam requestOfGetComment: RequestOfGetComment): String{
        TODO()
    }

    @PostMapping("/api/board/{boardId}/comment")
    fun createComment(boardId : String, @AuthenticationPrincipal authUser: AuthUser, @RequestBody requestOfCreateComment: RequestOfCreateComment): ResponseEntity<Response<ResponseOfCreateComment>>{
        var commentEntity = commentService.createComment(boardId, authUser, requestOfCreateComment)
        var responseOfCreateComment = ResponseOfCreateComment(
            boardId = commentEntity.board.isbn,
                userId = commentEntity.userId,
                username = commentEntity.username,
                page = commentEntity.page,
                createdAt = commentEntity.createdAt,
                content = commentEntity.content
        )

        return ResponseEntity(Response(
                status = Response.Status(message = "댓글이 등록되었습니다."),
                content = responseOfCreateComment
        ), HttpStatus.OK)
    }
}