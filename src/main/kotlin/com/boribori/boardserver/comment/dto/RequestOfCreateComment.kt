package com.boribori.boardserver.comment.dto

data class RequestOfCreateComment(
        val content: String,
        val page: Int
) {
}