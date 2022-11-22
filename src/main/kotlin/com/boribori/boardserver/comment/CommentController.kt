package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.comment.dto.RequestOfCreateComment
import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.boribori.boardserver.comment.dto.ResponseOfCreateComment
import com.boribori.boardserver.comment.dto.ResponseOfGetCommentList
import com.boribori.boardserver.common.Response
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class CommentController (
        private val commentService: CommentService
        ){

    @GetMapping("/api/board/{boardId}/comment")
    fun getComment(@PathVariable boardId : String, requestOfGetComment: RequestOfGetComment, @PageableDefault(page = 0, size = 5) pageable: Pageable): ResponseEntity<Response<ResponseOfGetCommentList>>{
        var getCommentList = commentService.getComment(boardId, requestOfGetComment, pageable)
        return ResponseEntity(
            Response(
                    status = Response.Status("$boardId 의 댓글이 정상적으로 조회되었습니다."),
                    content = getCommentList
            ),
                HttpStatus.OK
        )
    }

    @PostMapping("/api/board/{boardId}/comment")
    fun createComment(@PathVariable boardId : String, @AuthenticationPrincipal authUser: AuthUser, @RequestBody requestOfCreateComment: RequestOfCreateComment): ResponseEntity<Response<ResponseOfCreateComment>>{
        var commentEntity = commentService.createComment(boardId, authUser, requestOfCreateComment)
        var responseOfCreateComment = ResponseOfCreateComment(
            boardId = commentEntity.board.isbn,
                userId = commentEntity.userId,
                username = commentEntity.userNickname,
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