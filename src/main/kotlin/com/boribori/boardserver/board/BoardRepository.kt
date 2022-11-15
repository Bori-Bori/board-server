package com.boribori.boardserver.board

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BoardRepository: JpaRepository<Board, String>, BoardCustomRepository{
    fun findByIsbn(id: String): Board?

    @Query("select distinct b from Board b left join fetch b.commentList where b.isbn = ?1")
    fun findByIsbnFetch(id: String): Board?
}