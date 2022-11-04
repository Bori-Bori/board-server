package com.boribori.boardserver.board

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository: JpaRepository<Board, String>{
    fun findByIsbn(id: String): Board?
}