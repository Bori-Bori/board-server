package com.boribori.boardserver.board.dto.request

data class RequestOfSearchBooks(

        val category1: String? = null,
        val category2: String? = null,
        val category3: String? = null,
        val keyword: String? = null,
        val queryType: String = "keyword" //keyword, title, author
)