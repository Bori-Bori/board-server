package com.boribori.boardserver.board

import com.boribori.boardserver.comment.dto.RequestOfGetComment
import org.springframework.data.domain.Page
import java.awt.print.Pageable

interface BoardCustomRepository {
    fun getBoard(requestOfGetComment: RequestOfGetComment, pageable: Pageable) : Page<Board>
}