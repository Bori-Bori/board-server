package com.boribori.boardserver.board.exception

data class NotFoundBookException(
        val msg : String
) : RuntimeException()
