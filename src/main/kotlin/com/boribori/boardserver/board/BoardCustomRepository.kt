package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.request.RequestOfSearchBooks
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


interface BoardCustomRepository {
    fun searchAllBoards(requestOfSearchBooks: RequestOfSearchBooks, pageable: Pageable) : Slice<Board>
}