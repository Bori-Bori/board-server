package com.boribori.boardserver.reply

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.common.Response
import com.boribori.boardserver.reply.dto.RequestOfCreateReply
import com.boribori.boardserver.reply.dto.ResponseOfCreateReply
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
                    @RequestParam commentId: String)
    : ResponseEntity<Response<ResponseOfCreateReply>> {
        return ResponseEntity(
                Response(
                        status = Response.Status(message = "댓글이 등록되었습니다."),
                        content = replyService.createReply(commentId, authUser, requestOfCreateReply)
                ),
                HttpStatus.OK
        )
    }
}