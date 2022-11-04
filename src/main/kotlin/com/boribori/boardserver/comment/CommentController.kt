package com.boribori.boardserver.comment

import com.boribori.boardserver.auth.dto.AuthUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController {

    @GetMapping("/api/sample")
    fun getComment(boardId : String, @AuthenticationPrincipal authUser: AuthUser): String{
        return authUser.id + "---" + boardId
    }
}