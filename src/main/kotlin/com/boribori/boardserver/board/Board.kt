package com.boribori.boardserver.board


import com.boribori.boardserver.comment.Comment
import lombok.Builder
import lombok.Getter
import java.time.LocalDate
import java.util.*
import javax.persistence.*


@Getter
@Builder
@Entity
class Board {

    @Id
    private val isbn: UUID = UUID.randomUUID()

    @Column
    private var title: String? = null

    @Column
    private var author: String? = null

    @Column
    private var pubDate: LocalDate? = null

    @Column
    private var category1: String? = null

    @Column
    private var category2: String? = null

    @Column
    private var category3: String? = null

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "board")
    private var commentList = mutableListOf<Comment>()

}