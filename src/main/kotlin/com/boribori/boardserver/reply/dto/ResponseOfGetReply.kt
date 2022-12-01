package com.boribori.boardserver.reply.dto

import java.time.LocalDateTime

data class ResponseOfGetReply(

        val reply: String,
        val createdAt: LocalDateTime,
        val userId: String,
        val userNickname: String,
        val userProfileImagePath : String? = null
)