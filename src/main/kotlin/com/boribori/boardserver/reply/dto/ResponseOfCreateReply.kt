package com.boribori.boardserver.reply.dto

import java.time.LocalDateTime

data class ResponseOfCreateReply(
        val userId: String,
        val userNickname: String,
        val createdAt: LocalDateTime,
        val reply : String
)