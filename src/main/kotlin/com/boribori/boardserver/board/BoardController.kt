package com.boribori.boardserver.board

import com.boribori.boardserver.board.dto.DtoOfGetBoard
import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class BoardController(
        private val boardService: BoardService
) {

    @GetMapping("/api/board/{boardId}")
    fun getBoard(@PathVariable boardId: String): String{

        return boardId;
    }

    @GetMapping("/api/boards")
    fun getBoardList(@RequestParam request: RequestOfGetBooks){



    }
}