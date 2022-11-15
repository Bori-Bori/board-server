package com.boribori.boardserver.event

import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.comment.CommentService
import com.boribori.boardserver.comment.dto.EventOfUpdateNickname
import com.google.gson.Gson
import org.springframework.context.event.EventListener
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class CommentEventHandler(
        private val kafkaTemplate: KafkaTemplate<String, Any>,
        private val COMMENT_TOPIC: String = "bori",
        private val commentService: CommentService
) {

    @Async
    @EventListener
    fun alertReply(comment : Comment){
        kafkaTemplate.send(COMMENT_TOPIC, comment);
    }

    @KafkaListener(topics = ["bori"], groupId = "bori")
    fun consumeNicknameEvent(dto : String){
        println("이벤트 발행됨~")
        var gson = Gson()

        var event = gson.fromJson(dto, EventOfUpdateNickname::class.java);

        println("id = " + event.id)
        println("nickname = " + event.nickname)
    }
}