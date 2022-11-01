package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import org.springframework.stereotype.Service

@Service
class BoardService (
        private val boardRepository: BoardRepository
        ){

        fun getBoardList(requestOfGetBooks: RequestOfGetBooks){

        }
}