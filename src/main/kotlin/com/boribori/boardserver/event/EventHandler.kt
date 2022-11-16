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
        println("event 왔음~")
        var dto = EventOfPublishReplyAlarm(
                replyId = reply.id,
                userId = reply.userId,
                commentId = reply.comment.id,
                content = reply.content,
                createdAt = reply.createdAt
        )
        var json = objectMapper.writeValueAsString(dto)
        println("json = $json")
        kafkaTemplate.send("reply", json)
    }
}