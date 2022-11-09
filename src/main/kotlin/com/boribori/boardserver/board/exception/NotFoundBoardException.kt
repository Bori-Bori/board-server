package com.boribori.boardserver.board.exception

import java.lang.RuntimeException

data class NotFoundBoardException(
        val msg: String
) : RuntimeException(

) {
}