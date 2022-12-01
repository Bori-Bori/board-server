package com.boribori.boardserver.event.dto

import java.time.LocalDateTime

data class EventOfPublishReplyAlarm(
        val commentUserId : String,
        val commentId : String,
        val replyUserNickname: String,
        val commentContent: String,
        val replyId : String,
        val replyContent : String,
        val boardId : String,
        val createdAt : String,
        val page : Int

)