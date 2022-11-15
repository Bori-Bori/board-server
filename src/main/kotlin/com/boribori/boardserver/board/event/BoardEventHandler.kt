package com.boribori.boardserver.board.event

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.board.BoardService
import com.boribori.boardserver.comment.Comment
import lombok.extern.slf4j.Slf4j
import org.springframework.context.event.EventListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener


@Component
class BoardEventHandler(
        private final val boardService: BoardService,
        private final val kafkaTemplate: KafkaTemplate<String, Any>,
        private final val COMMENT_TOPIC: String = "bori"
) {
    @Async
    @EventListener
    fun viewCount(isbn : String){
        var boardEntity = boardService.getBoardEntity2(isbn)

        boardEntity.updateViewCount(1L)

        boardService.saveBoardEntity(boardEntity)

        return
    }



}