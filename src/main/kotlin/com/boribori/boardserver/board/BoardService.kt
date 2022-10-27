package com.boribori.boardserver.board

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardService (
        private val boardRepository: BoardRepository
        ){

    fun getBoard(boardId: UUID): Board{

    }
}