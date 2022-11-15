package com.boribori.boardserver.event

import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.comment.dto.EventOfUpdateNickname
import com.boribori.boardserver.reply.ReplyService
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
        private val replyService: ReplyService
) {

    @Async
    @EventListener
    fun alertReply(comment : Comment){
        kafkaTemplate.send(COMMENT_TOPIC, comment);
    }

    @KafkaListener(topics = ["bori"], groupId = "bori")
    fun nicknameEvent(dto : String){

        var gson = Gson()

        var event = gson.fromJson(dto, EventOfUpdateNickname::class.java);

        commentService.updateNickname(event)
        replyService.updateNickname(event)
    }
}