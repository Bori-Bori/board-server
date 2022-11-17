package com.boribori.boardserver.event

import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.comment.dto.EventOfUpdateNickname
import com.boribori.boardserver.event.dto.EventOfPublishReplyAlarm
import com.boribori.boardserver.reply.Reply
import com.boribori.boardserver.reply.ReplyService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


@Component
class EventHandler(
        private val kafkaTemplate: KafkaTemplate<String, Any>,
        private val COMMENT_TOPIC: String = "bori",
        private val commentService: CommentService,
        private val replyService: ReplyService,
        private val objectMapper: ObjectMapper
) {

    @Async
    @EventListener
    fun alertReply(comment : Comment){
        kafkaTemplate.send(COMMENT_TOPIC, comment);
    }

    @KafkaListener(topics = ["nickname"], groupId = "foo")
    fun nicknameEvent(dto : String){

        var gson = Gson()

        var event = gson.fromJson(dto, EventOfUpdateNickname::class.java);

        commentService.updateNickname(event)
        replyService.updateNickname(event)
    }

    @Async
    @EventListener
    fun alarmComment(reply: Reply){
        var dto = EventOfPublishReplyAlarm(
                replyId = reply.id,
                replyUserNickname = reply.userNickname,
                commentId = reply.comment.id,
                commentContent = reply.comment.content,
                boardId = reply.comment.board.isbn,
                commentUserId = reply.comment.userId,
                createdAt = reply.createdAt,
                page = reply.comment.page,
                replyContent = reply.content
        )

        var json = objectMapper.writeValueAsString(dto)
        kafkaTemplate.send("reply", json)
    }
}