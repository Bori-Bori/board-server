package com.boribori.boardserver.board.event

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.board.BoardService
import lombok.extern.slf4j.Slf4j
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener


@Component
class BoardEventHandler(
        private final val boardService: BoardService
) {
    @Async
    @EventListener
    fun viewCount(isbn : String){
        var boardEntity = boardService.getBoardEntity2(isbn)

        boardEntity.updateViewCount(1L)

        boardService.saveBoardEntity(boardEntity)

        return
    }

    @Async
    @EventListener
    fun viewCount(boardEntity : Board){
        Thread.sleep(5000L)
        println("5초 후")
        boardEntity.updateViewCount(1L)

        return
    }

}