package com.boribori.boardserver.reply.dto

import java.time.LocalDateTime

data class ResponseOfCreateReply(
        val writer: String,
        val createdAt: LocalDateTime,
        val reply : String
)