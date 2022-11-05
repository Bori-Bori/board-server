package com.boribori.boardserver.comment.dto

import java.time.LocalDateTime

data class ResponseOfCreateComment(
        val createdAt : LocalDateTime,
        val username : String,
        val userId : String,
        val boardId : String,
        val content : String,
        val page : Int
) {
}