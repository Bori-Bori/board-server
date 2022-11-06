package com.boribori.boardserver.comment.dto

data class RequestOfGetComment(
        val order : String? = null,
        val pageNum : Int? = null,
        val size : Int? = 5,
        val offset : Int? = null
) {
}