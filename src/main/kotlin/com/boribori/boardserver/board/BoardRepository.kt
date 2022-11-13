package com.boribori.boardserver.board

import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, String>, BoardCustomRepository{
    fun findByIsbn(id: String): Board?
}