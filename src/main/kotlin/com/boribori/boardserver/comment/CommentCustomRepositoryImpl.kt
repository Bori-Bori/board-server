package com.boribori.boardserver.comment

import com.boribori.boardserver.comment.dto.RequestOfGetComment
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import java.awt.print.Pageable

class CommentCustomRepositoryImpl(
        private val query: JPAQueryFactory
) : CommentCustomRepository {
    override fun getComment(requestOfGetComment: RequestOfGetComment, pageable: Pageable): Page<Comment> {
        TODO("Not yet implemented")
    }


}