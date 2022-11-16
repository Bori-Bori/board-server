package com.boribori.boardserver.event.dto

import java.time.LocalDateTime

data class EventOfPublishReplyAlarm(
        val userId : String,
        val commentId : String,
        val replyId : String,
        val content : String,
        val createdAt : LocalDateTime,
)