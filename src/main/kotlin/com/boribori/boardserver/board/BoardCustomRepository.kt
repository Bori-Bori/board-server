package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfSearchBoards
import org.springframework.data.domain.Pageable


interface BoardCustomRepository {
    fun searchAllBoards(requestOfSearchBooks: RequestOfSearchBoards, pageable: Pageable) : MutableList<Board>
}