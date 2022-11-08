package com.boribori.boardserver.comment

import com.boribori.boardserver.board.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jpa.repository.JpaRepository


interface CommentRepository : JpaRepository<Comment, String>{

    fun findByBoard(board: Board, pageable: Pageable): Slice<Comment>
    fun findByBoardAndPage(board: Board, page: Int, pageable: Pageable) : Slice<Comment>
    fun findByBoard(board: Board): Comment
}