package com.boribori.boardserver.board.dto.response

import java.time.LocalDate

data class ResponseOfGetBoard(
        val isbn: String,

        var title: String? = null,


        var author: String? = null,


        var pubDate: LocalDate? = null,


        var category1: String? = null,


        var category2: String? = null,


        var category3: String? = null,

        var description: String? = null,
        var publisher : String? = null,
        var imagePath : String? = null
) {
}