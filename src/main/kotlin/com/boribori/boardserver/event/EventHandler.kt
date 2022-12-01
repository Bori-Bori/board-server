package com.boribori.boardserver.event

import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.comment.dto.EventOfUpdateNickname
import com.boribori.boardserver.event.dto.EventOfPublishReplyAlarm
import com.boribori.boardserver.reply.Reply
import com.boribori.boardserver.reply.ReplyService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.gson.Gson
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter


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

    @KafkaListener(topics = ["profile"], groupId = "foo")
    fun nicknameEvent(dto : String){

        var gson = Gson()

        var event = gson.fromJson(dto, EventOfUpdateNickname::class.java);

        commentService.updateProfile(event)
        replyService.updateProfile(event)
    }

    @Async
    @EventListener
    fun alarmComment(reply: Reply){

        var objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

        var dto = EventOfPublishReplyAlarm(
                replyId = reply.id,
                replyUserNickname = reply.userNickname,
                commentId = reply.comment.id,
                commentContent = reply.comment.content,
                boardId = reply.comment.board.isbn,
                commentUserId = reply.comment.userId,
                createdAt = reply.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                page = reply.comment.page,
                replyContent = reply.content
        )
        print("date test = " + dto.createdAt)

        var json = objectMapper.writeValueAsString(dto)

        kafkaTemplate.send("reply", json)
    }

}