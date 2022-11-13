package com.boribori.boardserver.board.dto.request

data class RequestOfSearchBooks(
        var keyword: String = "",
        var queryType: String
)
