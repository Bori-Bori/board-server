package com.boribori.boardserver.comment

import com.boribori.boardserver.board.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface CommentRepository : JpaRepository<Comment, String>{

    fun findByBoard(board: Board, pageable: Pageable): Page<Comment>
    fun findByBoard(board: Board): Comment
}