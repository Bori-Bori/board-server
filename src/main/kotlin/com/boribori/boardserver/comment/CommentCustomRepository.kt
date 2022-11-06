package com.boribori.boardserver.comment

import com.boribori.boardserver.comment.dto.RequestOfGetComment
import org.springframework.data.domain.Page
import java.awt.print.Pageable

interface CommentCustomRepository {
    fun getComment(requestOfGetComment: RequestOfGetComment, pageable: Pageable) : Page<Comment>
}