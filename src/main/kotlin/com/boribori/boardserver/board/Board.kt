package com.boribori.boardserver.board


import com.boribori.boardserver.board.dto.response.ResponseOfGetBoard
import com.boribori.boardserver.comment.Comment
import com.boribori.boardserver.common.ResponseOfGetBookContent
import java.time.LocalDate
import javax.persistence.*


@Entity
class Board {
    @Id
    lateinit var isbn: String

    @Column
    var title: String? = null

    @Column
    var author: String? = null

    @Column
    var pubDate: LocalDate? = null

    @Column
    var category1: String? = null

    @Column
    var category2: String? = null

    @Column
    var category3: String? = null

    @Column
    var publisher: String? = null

    @Column
    var viewCount: Long = 0

    @Column
    var imagePath: String? = null

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "board")
    var commentList: MutableList<Comment> = mutableListOf<Comment>()
    fun of(content : ResponseOfGetBookContent) : Board {
        this.isbn = content.isbn.toString()
        this.title = content.title
        this.imagePath = content.imagePath
        this.author = content.author
        this.imagePath = content.imagePath
        this.category1 = content.category1
        this.category2 = content.category2
        this.category3 = content.category3
        this.publisher = content.publisher
        this.pubDate = content.pubDate
        this.commentList = mutableListOf()

        return this
    }

    fun updateViewCount(count : Long){
        this.viewCount = viewCount + count;
    }


}