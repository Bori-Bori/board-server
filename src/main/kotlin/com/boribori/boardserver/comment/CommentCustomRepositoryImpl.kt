package com.boribori.boardserver.comment

import com.querydsl.jpa.impl.JPAQueryFactory

class CommentCustomRepositoryImpl(
        private val query: JPAQueryFactory
) : CommentCustomRepository {

}