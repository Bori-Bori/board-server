package com.boribori.boardserver.board

import com.boribori.boardserver.auth.dto.AuthUser
import com.boribori.boardserver.board.dto.request.RequestOfGetBooks
import com.boribori.boardserver.board.dto.request.RequestOfSearchBoards
import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.board.dto.response.ResponseOfGetBooks
import com.boribori.boardserver.board.dto.response.ResponseOfSearchBoards
import com.boribori.boardserver.board.redis.BoardRepositoryRedis
import com.boribori.boardserver.common.Response
import com.boribori.boardserver.util.RequestUtil
import com.boribori.boardserver.util.dto.ResponseOfGetBook
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(
        private val boardService: BoardService,
        private val requestUtil: RequestUtil,
        private val eventPublisher: ApplicationEventPublisher
) {

    @GetMapping("/api/board/{boardId}")
    fun getBoard(@PathVariable boardId: String): ResponseEntity<Response<ResponseOfGetBoard>>{
        var boardEntity = boardService.getBoard(boardId);
        eventPublisher.publishEvent(boardEntity.isbn!!)

        return ResponseEntity(Response(
                content = boardEntity,
                status = Response.Status("성공적으로 조회되었습니다.")
        ), HttpStatus.OK);
    }

    @GetMapping("/api/board/search")
    fun getBooks(requestOfGetBooks: RequestOfGetBooks): ResponseEntity<Response<ResponseOfGetBooks>>{

        return ResponseEntity(Response(
                content = boardService.getBooks(requestOfGetBooks),
                status = Response.Status("성공적으로 조회되었습니다.")
        ), HttpStatus.OK);

    }

    @GetMapping("/api/boards")
    fun searchBoards(requestOfSearchBooks : RequestOfSearchBoards, @PageableDefault(size = 5, page = 0) pageable: Pageable): ResponseEntity<Response<ResponseOfSearchBoards>>{

        return ResponseEntity(Response(
                content = boardService.getBoards(requestOfSearchBooks, pageable),
                status = Response.Status("성공적으로 조회되었습니다.")
        ), HttpStatus.OK);
    }


    @GetMapping("/api/board/{boardId}/abc")
    fun getISBN(@RequestParam isbn : String, @AuthenticationPrincipal authUser: AuthUser): ResponseOfGetBook? {
        return requestUtil.getIsbn(isbn);
    }


}