package com.boribori.boardserver.board.dto.request

data class RequestOfGetBooks (
        var keyword: String = "",
        var queryType: String = "Title",
        var page: Int = 0,
        var size: Int = 5
)