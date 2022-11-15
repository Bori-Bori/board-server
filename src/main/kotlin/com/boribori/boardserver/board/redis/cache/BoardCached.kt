package com.boribori.boardserver.board.redis.cache

import com.boribori.boardserver.common.ResponseOfGetBookContent
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDate
import javax.persistence.Id

@RedisHash(value = "board")
data class BoardCached(
        @get:Id
        var id: String? = null,


        var title: String? = null,


        var author: String? = null,


        var pubDate: LocalDate? = null,


        var category1: String? = null,


        var category2: String? = null,


        var category3: String? = null,


        var publisher: String? = null,


        var viewCount: Long? = 1,

        var imagePath: String? = null,

        var description: String? =null,
){


    fun of(content : ResponseOfGetBookContent) : BoardCached {
        this.id = content.isbn.toString()
        this.title = content.title
        this.imagePath = content.imagePath
        this.author = content.author
        this.imagePath = content.imagePath
        this.category1 = content.category1
        this.category2 = content.category2
        this.category3 = content.category3
        this.publisher = content.publisher
        this.viewCount = 1
        this.pubDate = content.pubDate

        return this
    }

}