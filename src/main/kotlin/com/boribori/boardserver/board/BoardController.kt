package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.DtoOfGetBoard
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class BoardController(
        private val boardService: BoardService
) {

    @GetMapping("/api/board/{boardId}")
    fun getBoard(@PathVariable boardId: UUID): UUID{

        return boardId;
    }
}