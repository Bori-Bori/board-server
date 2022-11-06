package com.boribori.boardserver.board

import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import java.awt.print.Pageable

class BoardCustomRepositoryImpl(
        private val query: JPAQueryFactory
) : BoardCustomRepository {
    override fun getBoard(requestOfGetComment: RequestOfGetComment, pageable: Pageable): Page<Board> {
        TODO("파라미터 수정 필요")
    }

}