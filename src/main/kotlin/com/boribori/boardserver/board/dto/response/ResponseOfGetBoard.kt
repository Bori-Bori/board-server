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
    private var imagePath : String? = null

    fun of(content : ResponseOfGetBookContent) : ResponseOfGetBoard{
        this.isbn = content.isbn.toString()
        this.title = content.title
        this.imagePath = content.imagePath
        this.author = content.author
        this.description = content.description
        this.category1 = content.category1
        this.category2 = content.category2
        this.category3 = content.category3
        this.publisher = content.publisher
        this.imagePath = content.imagePath
        this.pubDate = content.pubDate

        return this
    }

    fun of(board : Board) : ResponseOfGetBoard{
        this.isbn = board.isbn
        this.title = board.title
        this.imagePath = board.imagePath
        this.author = board.author
        this.category1 = board.category1
        this.category2 = board.category2
        this.category3 = board.category3
        this.publisher = board.publisher
        this.imagePath = board.imagePath
        this.pubDate = board.pubDate


        return this
    }


}