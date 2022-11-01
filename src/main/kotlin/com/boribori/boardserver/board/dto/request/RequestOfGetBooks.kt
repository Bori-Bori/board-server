package com.boribori.boardserver.board.dto.request

class RequestOfGetBooks {
    val query: String? = null;
    val queryType: String = "Keyword";
    val start: Int = 1;
    val maxResults: Int = 5;

}