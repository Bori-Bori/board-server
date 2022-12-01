package com.boribori.boardserver.comment.dto

import java.time.LocalDateTime

data class ResponseOfGetComment(
        val id: String,
        val comment:String,
        val createdAt : LocalDateTime,
        val writer : String,
        val replyNum : Int,
        val page: Int,
        val userProfileImagePath: String? = null

) {
}