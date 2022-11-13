package com.boribori.boardserver.board.dto.response

import com.boribori.boardserver.board.Board
import com.boribori.boardserver.common.ResponseOfGetBookContent
import java.time.LocalDate

class ResponseOfGetBoard{
    var isbn: String? = null

    var title: String? = null


    var author: String? = null


    var pubDate: LocalDate? = null


    var category1: String? = null


    var category2: String? = null


    var category3: String? = null

    var description: String? = null
    var publisher : String? = null
    var imagePath : String? = null
    fun of(content : ResponseOfGetBookContent) : ResponseOfGetBoard{
        this.isbn = content.isbn.toString()
        this.title = content.title
        this.imagePath = content.imagePath
        this.author = content.author

        return this
    }

    fun of(board : Board) : ResponseOfGetBoard{
        this.isbn = board.isbn
        this.title = board.title
        this.imagePath = board.imagePath
        this.author = board.author

        return this
    }


}