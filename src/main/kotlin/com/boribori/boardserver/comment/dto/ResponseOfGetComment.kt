package com.boribori.boardserver.comment.dto

import java.time.LocalDateTime

data class ResponseOfGetComment(
        val comment:String,
        val createdAt : LocalDateTime,
        val writer : String,
        val replyNum : Int
) {
}