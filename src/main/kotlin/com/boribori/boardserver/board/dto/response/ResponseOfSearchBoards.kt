package com.boribori.boardserver.board.dto.response

data class ResponseOfSearchBoards(
        val isbn: String,
        val title : String?,
        val imagePath : String?,
        val author : String?,
        val commentCount : Int?
)