package com.boribori.boardserver.reply

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.common.Response
import com.boribori.boardserver.reply.dto.RequestOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfGetReplyList
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class ReplyController(
        private val replyService: ReplyService
) {

    @PostMapping("/api/comment/{commentId}/reply")
    fun createReply(@RequestBody requestOfCreateReply: RequestOfCreateReply,
                    @AuthenticationPrincipal authUser: AuthUser,
                    @PathVariable commentId: String)
    : ResponseEntity<Response<ResponseOfCreateReply>> {
        return ResponseEntity(
                Response(
                        status = Response.Status(message = "댓글이 등록되었습니다."),
                        content = replyService.createReply(commentId, authUser, requestOfCreateReply)
                ),
                HttpStatus.OK
        )
    }

    @GetMapping("/api/comment/{commentId}/reply")
    fun getReplyList(@RequestParam commentId: String,
                     @PageableDefault(size = 5, page = 0, sort = ["createdAt"], direction = Sort.Direction.DESC)
                     pageable: Pageable): ResponseEntity<Response<ResponseOfGetReplyList>>{

        var result = replyService.getReplyList(commentId, pageable);

        return ResponseEntity(
                Response(
                        status = Response.Status(message = "정상적으로 조회되었습니다."),
                        content = result
                ),
                HttpStatus.OK
        )
    }

}